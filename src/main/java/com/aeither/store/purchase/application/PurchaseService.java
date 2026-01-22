package com.aeither.store.purchase.application;

import com.aeither.store.administration.domain.model.Company;
import com.aeither.store.administration.domain.model.User;
import com.aeither.store.administration.domain.repository.UserDomainRepository;
import com.aeither.store.assests.application.AssetService;
import com.aeither.store.assests.domain.model.Asset;
import com.aeither.store.common.domain.AuthenticationContext;
import com.aeither.store.purchase.Api.dtos.PurchaseItemRequest;
import com.aeither.store.purchase.Api.dtos.PurchaseRequest;
import com.aeither.store.purchase.domain.model.Purchase;
import com.aeither.store.purchase.domain.model.PurchaseItem;
import com.aeither.store.purchase.domain.repository.PurchaseRepository;
import com.aeither.store.stock.application.StockService;
import com.aeither.store.stock.domain.model.Stock;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PurchaseService {

    private final PurchaseRepository purchaseRepository;
    private final AssetService assetService;
    private final StockService stockService;
    private final UserDomainRepository userRepository;
    private final AuthenticationContext authenticationContext;

    @Transactional
    public Purchase createPurchase(PurchaseRequest request) {
        User currentUser = getCurrentUser();
        if (currentUser == null || currentUser.getCompany() == null) {
            throw new IllegalStateException("User context not found. Cannot create purchase.");
        }
        Company company = currentUser.getCompany();

        Purchase purchase = new Purchase();
        purchase.setPurchaseNumber("PUR-" + System.currentTimeMillis()); // Simple ID generation
        purchase.setCompany(company);
        purchase.setSupplier(request.supplier());
        purchase.setNotes(request.notes());
        purchase.setStatus("COMPLETED"); // Auto-complete for now
        purchase.setTotalAmount(Double.parseDouble(request.grandTotal()));

        for (PurchaseItemRequest itemRequest : request.items()) {
            Asset asset = assetService.findById(itemRequest.assetId());
            if (asset == null)
                continue;

            PurchaseItem item = new PurchaseItem();
            item.setAsset(asset);
            item.setQuantity(itemRequest.quantity().intValue());
            item.setUnitPrice(itemRequest.price());
            item.setTotalPrice(itemRequest.total());

            purchase.addItem(item);

            // Update Stock
            Stock stockUpdate = new Stock();
            stockUpdate.setAsset(asset);
            stockUpdate.setQuantity(itemRequest.quantity().intValue());
            stockService.saveStock(stockUpdate);
        }

        return purchaseRepository.save(purchase);
    }

    public List<Purchase> getMyPurchases() {
        User currentUser = getCurrentUser();
        if (currentUser == null || currentUser.getCompany() == null) {
            return List.of();
        }
        List<Purchase> purchases = purchaseRepository.findByCompanyOrderByCreatedTimeDesc(currentUser.getCompany());
        purchases.forEach(purchase -> {
            if (purchase.getItems() == null) {
                purchase.setItems(new ArrayList<>());
            }
        });
        return purchases;
    }

    private User getCurrentUser() {
        String username = authenticationContext.getCurrentUsername();
        return userRepository.findByUsername(username).orElse(null);
    }
}
