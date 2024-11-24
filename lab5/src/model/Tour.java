package model;

public class Tour {
    private String name;
    private String description;
    private boolean isBooked;
    private int price;

    public Tour(String name, String description, int price) {
        this.name = name;
        this.description = description;
        this.isBooked = false;
        this.price = price;
    }
}
