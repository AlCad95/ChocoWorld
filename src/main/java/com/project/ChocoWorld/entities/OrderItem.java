package com.project.ChocoWorld.entities;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "order_item")
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne
    @JoinColumn(name = "order_details_id")
    private OrderDetail orderDetails;

    @OneToOne
    private Product product;

    private int quantity;

    public OrderItem(OrderDetail orderDetails, Product product, int quantity) {
        this.orderDetails = orderDetails;
        this.product = product;
        this.quantity = quantity;
    }

    // для Thymeleaf
    public OrderItem() {
    }
}
