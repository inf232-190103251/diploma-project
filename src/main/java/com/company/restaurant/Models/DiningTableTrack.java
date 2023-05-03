package com.company.restaurant.Models;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

@Entity
@Table(name = "dining_table_track")
public class DiningTableTrack {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    public Long id;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "dining_tables_id_id", nullable = false)
    public DiningTables diningTablesid;

    @JsonBackReference
    @OneToOne
    @JoinColumn(name = "orderid_id", nullable = false,unique = true)
    public Order orderid;

    public DiningTableTrack(DiningTables diningTables_id, Order order_id) {
        this.diningTablesid = diningTables_id;
        this.orderid = order_id;
    }

    public DiningTableTrack() {

    }

    public Long getId() {
        return id;
    }


    public DiningTables getDiningTablesid() {
        return diningTablesid;
    }

    public void setDiningTablesid(DiningTables diningTablesid) {
        this.diningTablesid = diningTablesid;
    }

    public Order getOrderid() {
        return orderid;
    }

    public void setOrderid(Order orderid) {
        this.orderid = orderid;
    }
}
