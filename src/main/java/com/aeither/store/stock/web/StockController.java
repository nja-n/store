package com.aeither.store.stock.web;

import com.aeither.store.assests.application.AssetService;
import com.aeither.store.stock.application.StockService;
import com.aeither.store.stock.domain.model.Stock;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/stock")
@RequiredArgsConstructor
public class StockController {

    private final StockService stockService;
    private final AssetService assetService;

    @GetMapping
    public String index(Model model) {
        model.addAttribute("stocks", stockService.getMyCompanyStocks());
        model.addAttribute("assets", assetService.findAll());
        model.addAttribute("newStock", new Stock());
        return "stock/index";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Stock stock) {
        stockService.saveStock(stock);
        return "redirect:/stock";
    }
}
