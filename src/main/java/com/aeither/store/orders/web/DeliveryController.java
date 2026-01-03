package com.aeither.store.orders.web;

import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.aeither.store.administration.application.UserService;
import com.aeither.store.administration.domain.model.User;
import com.aeither.store.common.domain.AuthenticationContext;
import com.aeither.store.orders.application.OrderService;
import com.aeither.store.orders.application.PrintOrderService;
import com.aeither.store.orders.domain.model.Order;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/delivery")
@RequiredArgsConstructor
public class DeliveryController {

    private final OrderService orderService;
    private final UserService userService;
    private final AuthenticationContext authenticationContext;
    private final PrintOrderService printOrderService;

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

    @GetMapping("/print/{id}")
    public ResponseEntity<byte[]> printOrder(@PathVariable Long id, Model model) {
        Order order = orderService.findById(id);
        byte[] pdfBytes = printOrderService.printOrder(order);
        return ResponseEntity.ok()
            .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=order_" + order.getOrderNumber() + ".pdf")
            .contentType(MediaType.APPLICATION_PDF)
            .body(pdfBytes);
    }
}
