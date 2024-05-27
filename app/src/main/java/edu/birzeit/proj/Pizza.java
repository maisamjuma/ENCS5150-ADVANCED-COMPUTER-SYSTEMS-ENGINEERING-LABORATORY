package edu.birzeit.proj;

import java.util.ArrayList;

public class Pizza {

    private String name;

    public String getName() {
        return name;
    }
    public Pizza(String name) {
        this.name = name;
    }
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Pizza{" +
                "\nname= " + name +
                "\n}";
    }
}