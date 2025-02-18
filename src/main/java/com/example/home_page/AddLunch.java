package com.example.home_page;

public class AddLunch {
    private String day;
    private String dish;
    private String description;
    private int price;

    public AddLunch(String day, String dish, String description, int price) {
        this.day = day;
        this.dish = dish;
        this.description = description;
        this.price = price;
    }

    public String getDay() {
        return day;
    }

    public String getDish() {
        return dish;
    }

    public String getDescription() {
        return description;
    }

    public int getPrice() {
        return price;
    }

    public void setDish(String dish) {
        this.dish = dish;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
