package com.aeither.store.orders.web;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetailsDTO {
    private Long id;
    private String orderNumber;
    private String status;
    private Double totalAmount;
    private String storeName;
    private List<ItemDTO> items;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ItemDTO {
        private String assetName;
        private Integer quantity;
        private Double unitPrice;
        private Double totalPrice;
    }
}
