package miun.dt170g.antons;

import jakarta.inject.Named;

import java.io.Serializable;
import java.util.Objects;

public class Lunch{
    private String day;
    private String title;
    private String description;
    private int price;
    private String title2;
    private int price2;

    public Lunch(String day, String title, String description, int price, String title2, int price2) {
        this.day = day;
        this.title = title;
        this.description = description;
        this.price = price;
        this.title2 = title2;
        this.price2 = price2;
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

    public String getTitle2() {
        return title2;
    }

    public void setTitle2(String title2) {
        this.title2 = title2;
    }

    public int getPrice2() {
        return price2;
    }

    public void setPrice2(int price2) {
        this.price2 = price2;
    }
}
