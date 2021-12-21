package com.project.ChocoWorld.entities;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@Data
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private double sum;
    private LocalDateTime localDataTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "order_detail_id")
    private OrderDetail orderDetail;


    public Order() {
        orderDetail = new OrderDetail();
        orderDetail.setOrder(this);
    }

    @PrePersist
    void createdAt() {
        this.localDataTime = LocalDateTime.now();
    }

    public void calculateSum() {
        if (!orderDetail.getOrderItems().isEmpty()) {
            sum = orderDetail.getOrderItems().stream()
                    .map(orderItem -> orderItem.getProduct().getPrice() * orderItem.getQuantity())
                    .reduce((acc, x) -> acc + x)
                    .get();
        }
    }




    public String getDateTimeForDisplay() {
        return localDataTime.format(DateTimeFormatter.ofPattern("HH:mm, dd MMM uuuu", Locale.ENGLISH));
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", sum=" + sum +
                ", user=" + user +
                '}';


    }
}
