package miun.dt170g.antons;

import jakarta.inject.Named;

public class Lunch {
    private String day;
    private String title;
    private String description;
    private int price;

    public Lunch(String day, String title, String description, int price) {
        this.day = day;
        this.title = title;
        this.description = description;
        this.price = price;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
