package com.company.restaurant.Models;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "date_created", nullable = false)
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private ZonedDateTime dateCreated;

    @Column(name = "purchase_date")
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private ZonedDateTime purchaseDate;

    @Column(name = "done_date")
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private ZonedDateTime doneDate;

    @Column(name = "predicted_date")
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private ZonedDateTime predictedDate;

    private String status;

    @JsonIgnore
    @JsonManagedReference
    @OneToMany(mappedBy = "order")
    private List<OrderFood> orderProducts = new ArrayList<>();

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id")
    private Usr user;


    @Transient
    public Double getTotalOrderPrice() {
        double sum = 0D;
        List<OrderFood> orderProducts = getOrderProducts();
        for (OrderFood op : orderProducts) {
            sum += op.getTotalPrice();
        }
        return sum;
    }

    @Transient
    public int getNumberOfProducts() {
        return this.orderProducts.size();
    }

    public ZonedDateTime getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(ZonedDateTime dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<OrderFood> getOrderProducts() {
        return orderProducts;
    }

    public void setOrderProducts(List<OrderFood> orderProducts) {
        this.orderProducts = orderProducts;
    }

    public Long getId() {
        return id;
    }

    @Transient
    public Usr getUser() {
        return user;
    }


    public void setUser(Usr user) {
        this.user = user;
    }

    public String getOrderProductsString() {
        StringBuilder empty = new StringBuilder();
        for (OrderFood food : orderProducts) {
            empty.append(food.toString());
        }
        return empty.toString();
    }

    @Override
    public String toString() {
        return "Order{" +
                "order_id=" + id +
                ", dateCreated=" + dateCreated +
                ", status='" + status + '\'' +
                '}';
    }


    public ZonedDateTime getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(ZonedDateTime purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public ZonedDateTime getDoneDate() {
        return doneDate;
    }

    public void setDoneDate(ZonedDateTime doneDate) {
        this.doneDate = doneDate;
    }

    public ZonedDateTime getPredictedDate() {
        return predictedDate;
    }

    public void setPredictedDate(ZonedDateTime predictedDate) {
        this.predictedDate = predictedDate;
    }
}
