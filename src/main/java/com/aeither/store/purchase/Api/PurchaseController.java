package com.aeither.store.purchase.Api;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.http.ResponseEntity;

import com.aeither.store.purchase.Api.dtos.PurchaseRequest;

@Controller
@RequestMapping("/purchase")
@lombok.RequiredArgsConstructor
public class PurchaseController {

    private final com.aeither.store.purchase.application.PurchaseService purchaseService;
    private final com.aeither.store.assests.application.AssetService assetService;

    @PostMapping
    @ResponseBody
    public ResponseEntity<?> postPurchase(@RequestBody PurchaseRequest purchaseRequest) {
        try {
            purchaseService.createPurchase(purchaseRequest);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    public String getPurchase(Model model) {
        model.addAttribute("assets", assetService.findAll());
        model.addAttribute("purchases", purchaseService.getMyPurchases());
        return "purchase/index";
    }

}
