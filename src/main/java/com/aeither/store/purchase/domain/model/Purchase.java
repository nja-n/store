package com.aeither.store.purchase.domain.model;

import com.aeither.store.administration.domain.model.Company;
import com.aeither.store.common.domain.AuditableEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "purchases")
@Getter
@Setter
@NoArgsConstructor
public class Purchase extends AuditableEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String purchaseNumber;

    private String supplier;

    private String notes;

    private String status; // PENDING, COMPLETED, CANCELLED

    private Double totalAmount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    private Company company;

    @OneToMany(mappedBy = "purchase", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PurchaseItem> items;

    public void addItem(PurchaseItem item) {
        if (items == null) {
            items = new ArrayList<>();
        }
        items.add(item);
        item.setPurchase(this);
    }
}
