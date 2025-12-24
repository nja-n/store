package com.aeither.store.entities;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "assets")
public class Asset {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String brand;
    private String type;
    private String catogory;
    private Double price;
    private String description;
    private String imageUrl;
    private Integer quantity;
    private String status;
    
    @ManyToOne
    @JoinColumn(name = "added_user_id")
    private User addedUser;
    private Date addedDate;
}
