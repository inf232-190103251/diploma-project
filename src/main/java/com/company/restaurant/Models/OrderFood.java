package com.company.restaurant.Models;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
@Entity
@Table(name = "ORDER_FOOD")
public class OrderFood {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Food product;


    @Column(nullable = false)
    private Integer quantity;

    public OrderFood(Order order, Food product, Integer quantity) {
        this.order = order;
        this.product = product;
        this.quantity = quantity;
    }

    public OrderFood() {

    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    @Transient
    public Food getProduct() {
        return product;
    }

    @Transient
    public long getTotalPrice() {
        return getProduct().getPrice() * getQuantity();
    }

    public void setProduct(Food product) {
        this.product = product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "OrderFood{" +
                "order=" + order +
                ", product=" + product +
                ", quantity=" + quantity +
                '}';
    }
}