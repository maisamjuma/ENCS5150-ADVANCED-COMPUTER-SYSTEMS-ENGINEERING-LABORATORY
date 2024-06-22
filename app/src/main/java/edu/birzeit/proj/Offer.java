package edu.birzeit.proj;

import androidx.annotation.NonNull;

import java.util.Date;

public class Offer {
    private String type;
    private String size;
    private Float prize;
    private String date;

    // Default constructor
    public Offer() {
    }

    // Parameterized constructor
    public Offer(String type, String size, Float prize, String date) {
        this.type = type;
        this.size = size;
        this.prize = prize;
        this.date = date;
    }

    // Getter and Setter methods
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public Float getPrize() {
        return prize;
    }

    public void setPrize(Float prize) {
        this.prize = prize;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @NonNull
    @Override
    public String toString() {
        return "Offer{" +
                "type='" + type + '\'' +
                ", size='" + size + '\'' +
                ", prize=" + prize +
                ", date=" + date +
                '}';
    }
}