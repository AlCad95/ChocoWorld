package com.project.ChocoWorld.entities;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    private double price;
    private int inStock;

    public Product() {
    }

    public Product(String name, double price, int inStock) {
        this.name = name;
        this.price = price;
        this.inStock = inStock;
    }


}

