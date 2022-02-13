package com.example.alreef;

public class SupplierRecord {
    Integer ID;
    String name;
    String address;
    Integer phone;

    public SupplierRecord(Integer ID, String name, String address, Integer phone) {
        this.ID = ID;
        this.name = name;
        this.address = address;
        this.phone = phone;
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getPhone() {
        return phone;
    }

    public void setPhone(Integer phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "SupplierRecord{" +
                "ID=" + ID +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", phone=" + phone +
                '}';
    }
}
