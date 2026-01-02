package com.aeither.store.orders.infrastructure.repository;

import com.aeither.store.orders.domain.model.Order;
import com.aeither.store.orders.domain.repository.OrderDomainRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long>, OrderDomainRepository {
    List<Order> findByStore_Id(Long storeId);

    List<Order> findByCompanyId(Long companyId);
}
