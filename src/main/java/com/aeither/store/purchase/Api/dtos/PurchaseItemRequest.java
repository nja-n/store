package com.aeither.store.purchase.Api.dtos;

public record PurchaseItemRequest(
    Long assetId,
    Long quantity,
    Double price,
    Double tax,
    Double discount,
    Double total
) {

}
