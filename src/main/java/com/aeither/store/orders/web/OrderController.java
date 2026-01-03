package com.aeither.store.orders.web;

import com.aeither.store.administration.application.StoreService;
import com.aeither.store.administration.application.UserService;
import com.aeither.store.administration.domain.model.User;
import com.aeither.store.common.domain.AuthenticationContext;
import com.aeither.store.orders.application.OrderService;
import com.aeither.store.orders.domain.model.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final StoreService storeService;
    private final UserService userService;
    private final com.aeither.store.stock.application.StockService stockService;
    private final AuthenticationContext authenticationContext;

    @GetMapping
    public String index(Model model) {
        User currentUser = getCurrentUser();
        if (currentUser == null)
            return "redirect:/login";

        model.addAttribute("newOrder", new Order());

        if (currentUser.getStore() != null) {
            model.addAttribute("orders", orderService.findByStoreId(currentUser.getStore().getId()));
            model.addAttribute("stores", java.util.List.of(currentUser.getStore()));
            model.addAttribute("stocks", stockService.getMyCompanyStocks());
        } else if (currentUser.getCompany() != null) {
            model.addAttribute("orders", orderService.findByCompanyId(currentUser.getCompany().getId()));
            model.addAttribute("stores", storeService.findAll().stream()
                    // .filter(s -> currentUser.getCompany().getId().equals(s.getCompanyId()))
                    .toList());
            model.addAttribute("stocks", stockService.getMyCompanyStocks());
        } else {
            model.addAttribute("orders", orderService.findAll());
            model.addAttribute("stores", storeService.findAll());
            model.addAttribute("stocks", java.util.List.of()); // Or load all stocks if needed
        }

        return "orders/index";
    }

    private User getCurrentUser() {
        return userService.findByUsername(authenticationContext.getCurrentUsername());
    }
}
