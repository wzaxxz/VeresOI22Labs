package models;

public class Tour {
    private String name;
    private String description;
    private boolean isBooked;
    private int price;
    private int duration;

    public Tour(String name, String description, int price, int duration) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.duration = duration;
        this.isBooked = false;
    }

    public String getName() {
        return name;
    }

    public boolean isBooked() {
        return isBooked;
    }

    public int getPrice() {
        return price;
    }

    public int getDuration() {
        return duration;
    }

    public void bookTour() {
        if (!isBooked) {
            isBooked = true;
        }
    }

    public String getInfo() {
        return name + ", Ціна: " + price + ", Тривалість: " + duration + " днів" + (isBooked ? " (Заброньовано)" : "");
    }

    public String getFullInfo() {
        return name + ": " + description + ", Ціна: " + price + ", Тривалість: " + duration + " днів" + (isBooked ? " (Заброньовано)" : "");
    }

    public String toString() {
        return name + ": " + description + ", Ціна: " + price + ", Тривалість: " + duration + " днів";
    }
}
