package com.aeither.store.stock.application;

import com.aeither.store.administration.domain.model.User;
import com.aeither.store.administration.domain.repository.UserDomainRepository;
import com.aeither.store.stock.domain.model.Stock;
import com.aeither.store.stock.domain.repository.StockDomainRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StockService {

    private final StockDomainRepository stockRepository;
    private final UserDomainRepository userRepository;

    public List<Stock> getMyCompanyStocks() {
        User currentUser = getCurrentUser();
        if (currentUser != null && currentUser.getCompany() != null) {
            return stockRepository.findByCompany(currentUser.getCompany());
        }
        return Collections.emptyList();
    }

    public Stock saveStock(Stock stock) {
        User currentUser = getCurrentUser();
        if (currentUser != null && currentUser.getCompany() != null) {
            Optional<Stock> existingOpt = stockRepository.findByCompanyAndAsset(currentUser.getCompany(),
                    stock.getAsset());
            if (existingOpt.isPresent()) {
                Stock existing = existingOpt.get();
                existing.setQuantity(existing.getQuantity() + stock.getQuantity());
                return stockRepository.save(existing);
            } else {
                stock.setCompany(currentUser.getCompany());
                if (stock.getStatus() == null) {
                    stock.setStatus("ACTIVE");
                }
                return stockRepository.save(stock);
            }
        }
        throw new IllegalStateException("Only users associated with a company can manage stocks.");
    }

    private User getCurrentUser() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepository.findByUsername(username).orElse(null);
    }
}
