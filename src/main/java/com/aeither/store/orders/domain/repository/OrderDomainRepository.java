package com.aeither.store.orders.domain.repository;

import com.aeither.store.orders.domain.model.Order;
import java.util.List;
import java.util.Optional;

public interface OrderDomainRepository {
    Order save(Order order);

    List<Order> findAll();

    Optional<Order> findById(Long id);

    List<Order> findByStore_Id(Long storeId);

    List<Order> findByCompanyId(Long companyId);
}
