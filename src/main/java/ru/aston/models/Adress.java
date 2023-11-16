package ru.aston.models;


import java.util.Objects;

public class Adress {

    private int id;

    private int personId;

    private String addressFull;


    public Adress(int personId, String addressFull) {
        this.personId = personId;
        this.addressFull = addressFull;
    }

    public int getId() {
        return id;
    }

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    public String getAddressFull() {
        return addressFull;
    }

    public void setAddressFull(String addressFull) {
        this.addressFull = addressFull;
    }

    @Override
    public String toString() {
        return "Adress{" +
                "id=" + id +
                ", personId=" + personId +
                ", addressFull='" + addressFull + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Adress adress = (Adress) o;
        return id == adress.id && personId == adress.personId && Objects.equals(addressFull, adress.addressFull);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, personId, addressFull);
    }
}
