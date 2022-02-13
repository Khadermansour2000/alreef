package com.example.alreef;

public class MeterialRecord {
    Integer ID;
    String name;
    Double price;

    public MeterialRecord(Integer ID, String name, Double price) {
        this.ID = ID;
        this.name = name;
        this.price = price;
    }

    public Integer getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public Double getPrice() {
        return price;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

}
