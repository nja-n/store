package com.aeither.store.orders.web;

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

import java.util.List;

@Controller
@RequestMapping("/delivery")
@RequiredArgsConstructor
public class DeliveryController {

    private final OrderService orderService;
    private final UserService userService;
    private final AuthenticationContext authenticationContext;

    @GetMapping
    public String index(Model model) {
        User currentUser = userService.findByUsername(authenticationContext.getCurrentUsername());
        if (currentUser == null)
            return "redirect:/login";

        List<Order> orders;
        if (currentUser.getCompany() != null) {
            orders = orderService.findByCompanyId(currentUser.getCompany().getId());
        } else {
            orders = orderService.findAll();
        }

        model.addAttribute("orders", orders);
        return "orders/delivery";
    }
}
