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
    private OrderDetail orderDetail;

    @OneToOne
    private Product product;

    private int quantity;

    public OrderItem(OrderDetail orderDetail, Product product, int quantity) {
        this.orderDetail = orderDetail;
        this.product = product;
        this.quantity = quantity;
    }

    // для Thymeleaf
    public OrderItem() {
    }
}
