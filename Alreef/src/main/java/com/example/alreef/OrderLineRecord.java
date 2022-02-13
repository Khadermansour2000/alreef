package com.example.alreef;

public class OrderLineRecord {
    Integer NO_Order;
    String name;
    Integer id;
    Double price;
    Integer Qan;
    Double total;

    public OrderLineRecord(Integer NO_Order, String name, Integer ID, Double price, Integer qan) {
        this.id = ID;
        this.name = name;
        this.price = price;
        this.NO_Order = NO_Order;
        this.Qan = qan;
        this.total = qan * price;
    }

    public Integer getNO_Order() {
        return NO_Order;
    }

    public void setNO_Order(Integer NO_Order) {
        this.NO_Order = NO_Order;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getQan() {
        return Qan;
    }

    public void setQan(Integer qan) {
        Qan = qan;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

}
