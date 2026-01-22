package com.aeither.store.purchase.Api.dtos;

import java.util.List;

public record PurchaseRequest(
    // Long companyId,
    // String purchaseDate,
    List<PurchaseItemRequest> items,
    String supplier,
    String notes,
    String source,
    String status,
    String paymentMode,
    String paymentStatus,
    String amount,
    String tax,
    String discount,
    String grandTotal
) {
    
}
