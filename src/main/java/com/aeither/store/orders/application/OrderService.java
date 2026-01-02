package com.aeither.store.orders.application;

import com.aeither.store.orders.domain.model.Order;
import com.aeither.store.orders.domain.repository.OrderDomainRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.aeither.store.orders.domain.model.OrderItem;
import com.aeither.store.orders.web.OrderRequestDTO;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderDomainRepository orderRepository;

    private final com.aeither.store.administration.application.StoreService storeService;
    private final com.aeither.store.assests.application.AssetService assetService;
    private final com.aeither.store.stock.domain.repository.StockDomainRepository stockRepository;

    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    public List<Order> findByStoreId(Long storeId) {
        return orderRepository.findByStore_Id(storeId);
    }

    public List<Order> findByCompanyId(Long companyId) {
        return orderRepository.findByCompanyId(companyId);
    }

    public Order findById(Long id) {
        return orderRepository.findById(id).orElse(null);
    }

    public Order save(Order order) {
        if (order.getStatus() == null) {
            order.setStatus("PENDING");
        }
        return orderRepository.save(order);
    }

    @org.springframework.transaction.annotation.Transactional
    public Order placeOrder(OrderRequestDTO request, com.aeither.store.administration.domain.model.Company company) {
        Order order = new Order();
        order.setStore(storeService.findById(request.getStoreId()));
        order.setCompanyId(company.getId());
        order.setOrderNumber("ORD-" + System.currentTimeMillis());
        order.setStatus("PENDING");

        Double total = 0.0;
        for (OrderRequestDTO.ItemRequestDTO itemReq : request.getItems()) {
            OrderItem item = new OrderItem();
            item.setOrder(order);
            item.setAsset(assetService.findById(itemReq.getAssetId()));
            item.setQuantity(itemReq.getQuantity());
            item.setUnitPrice(itemReq.getPrice());
            item.setTotalPrice(itemReq.getQuantity() * itemReq.getPrice());
            order.getItems().add(item);
            total += item.getTotalPrice();

            // Stock Deduction
            com.aeither.store.stock.domain.model.Stock stock = stockRepository
                    .findByCompanyAndAsset(company, item.getAsset())
                    .orElseThrow(() -> new RuntimeException("Stock not found for asset: " + item.getAsset().getName()));

            if (stock.getQuantity() < item.getQuantity()) {
                throw new RuntimeException("Insufficient stock for asset: " + item.getAsset().getName());
            }
            stock.setQuantity(stock.getQuantity() - item.getQuantity());
            if (stock.getQuantity() == 0) {
                stock.setStatus("OUT_OF_STOCK");
            }
            stockRepository.save(stock);
        }

        order.setTotalAmount(total);
        return orderRepository.save(order);
    }

    @org.springframework.transaction.annotation.Transactional
    public void updateStatus(Long id, String status) {
        Order order = findById(id);
        if (order != null) {
            order.setStatus(status);
            orderRepository.save(order);
        }
    }
}
