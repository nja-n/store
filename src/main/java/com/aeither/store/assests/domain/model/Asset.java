package com.aeither.store.assests.domain.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import com.aeither.store.common.domain.AuditableEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "assets")
@Getter
@Setter
@NoArgsConstructor
public class Asset extends AuditableEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne
    private SubCategory subCategory;

    @ManyToOne
    private Brand brand;

    private String serialNumber;

    private String assetNumber;

    private String status;

    private String model;

    private Double mrp;
    private Double wholesalePrice;

    public void updateFullName() {
        StringBuilder sb = new StringBuilder();
        if (subCategory != null && subCategory.getCategory() != null) {
            sb.append(subCategory.getCategory().getName()).append(" ");
        }
        if (subCategory != null) {
            sb.append(subCategory.getName()).append(" ");
        }
        if (brand != null) {
            sb.append(brand.getName()).append(" ");
        }
        if (model != null && !model.isEmpty()) {
            sb.append(model);
        }
        this.name = sb.toString().trim();
    }
}
