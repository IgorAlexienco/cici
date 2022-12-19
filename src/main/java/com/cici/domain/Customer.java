package com.cici.domain;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "customers")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)   //strategy = GenerationType.IDENTITY
    @Column(name = "CUST_ID_PK")
    private Long id;

    private String name;

    @OneToMany(targetEntity= Order.class, cascade=CascadeType.ALL)
    @JoinColumn(name="CUST_ID_FK", referencedColumnName = "CUST_ID_PK")
    private List<Order> orders;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
}
