package com.example.alreef;

public class OrderRecord {
    private Integer Onumber;
    private Integer ID;
    private String date;
    private Double total;

    public OrderRecord(Integer onumber, Integer ID, String date, Double total) {
        Onumber = onumber;
        this.ID = ID;
        this.date = date;
        this.total = total;
    }

    public Integer getOnumber() {
        return Onumber;
    }

    public void setOnumber(Integer onumber) {
        Onumber = onumber;
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }
}
