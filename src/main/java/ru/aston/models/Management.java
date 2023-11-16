package ru.aston.models;


import java.util.Objects;

public class Management {

    private int id;

    private int addressId;

    private String nameCompany;

    public Management(int addressId, String nameCompany) {
        this.addressId = addressId;
        this.nameCompany = nameCompany;
    }

    public int getId() {
        return id;
    }


    public int getAddressId() {
        return addressId;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }

    public String getNameCompany() {
        return nameCompany;
    }

    public void setNameCompany(String nameCompany) {
        this.nameCompany = nameCompany;
    }

    @Override
    public String toString() {
        return "Management{" +
                "id=" + id +
                ", addressId=" + addressId +
                ", nameCompany='" + nameCompany + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Management that = (Management) o;
        return id == that.id && addressId == that.addressId && Objects.equals(nameCompany, that.nameCompany);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, addressId, nameCompany);
    }
}
