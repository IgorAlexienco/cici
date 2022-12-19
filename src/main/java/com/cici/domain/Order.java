package com.cici.domain;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)   //strategy = GenerationType.IDENTITY
    @Column(name = "ORDER_ID_PK")
    private Long id;
    private String name;
    private int quantity;
    private BigDecimal price;

    private String dispached;
    @Column(name = "CUST_ID_FK")
    private Long custIdFk;

    public String getDispached() {
        return dispached;
    }

    public void setDispached(String dispached) {
        this.dispached = dispached;
    }

    public Long getCustIdFk() {
        return custIdFk;
    }

    public void setCustIdFk(Long custIdFk) {
        this.custIdFk = custIdFk;
    }

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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

}
