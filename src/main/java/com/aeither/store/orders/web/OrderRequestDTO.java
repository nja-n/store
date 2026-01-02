package com.aeither.store.orders.web;

import lombok.Data;
import java.util.List;

@Data
public class OrderRequestDTO {
    private Long storeId;
    private List<ItemRequestDTO> items;

    @Data
    public static class ItemRequestDTO {
        private Long assetId;
        private Integer quantity;
        private Double price;
    }
}
