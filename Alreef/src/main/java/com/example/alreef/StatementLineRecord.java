package com.example.alreef;

public class StatementLineRecord {
    public Integer Number;
    public String date;
    public Integer id;
    public Double price;
    public Integer Qan;
    public Double total;

    public StatementLineRecord(Integer Number, String date, Integer id, Double price, Integer qan) {
        this.Number = Number;
        this.date = date;
        this.id = id;
        this.price = price;
        this.Qan = qan;
        this.total=Qan*price;
    }

    public Integer getNumber() {
        return Number;
    }

    public void setNumber(Integer oNumber) {
        this.Number = oNumber;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
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
