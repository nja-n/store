package com.aeither.store.orders.web;

import com.aeither.store.administration.application.UserService;
import com.aeither.store.administration.domain.model.User;
import com.aeither.store.common.domain.AuthenticationContext;
import com.aeither.store.orders.application.OrderService;
import com.aeither.store.stock.application.StockService;
import com.aeither.store.stock.domain.model.Stock;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderApiController {

    private final OrderService orderService;
    private final StockService stockService;
    private final UserService userService;
    private final AuthenticationContext authenticationContext;

    @GetMapping("/assets/search")
    public List<Stock> searchAssets(@RequestParam String query) {
        User currentUser = userService.findByUsername(authenticationContext.getCurrentUsername());
        if (currentUser == null || currentUser.getCompany() == null) {
            return List.of();
        }

        String lowerQuery = query.toLowerCase();
        return stockService.getMyCompanyCompanyStocks(currentUser.getCompany()).stream()
                .filter(stock -> stock.getAsset().getName().toLowerCase().contains(lowerQuery) ||
                        stock.getAsset().getModel().toLowerCase().contains(lowerQuery))
                .collect(Collectors.toList());
    }

    @PostMapping("/place")
    public ResponseEntity<?> placeOrder(@RequestBody OrderRequestDTO request) {
        User currentUser = userService.findByUsername(authenticationContext.getCurrentUsername());
        if (currentUser == null || currentUser.getCompany() == null) {
            return ResponseEntity.badRequest().body("Login required");
        }

        try {
            orderService.placeOrder(request, currentUser.getCompany());
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
