package com.example.musicalinstruments;

public class Item {
    private String itemNumber;
    private String itemName;
    private double price;
    private String imageUrl;

    public Item(String itemNumber, String itemName, double price, String imageUrl) {
        this.itemNumber = itemNumber;
        this.itemName = itemName;
        this.price = price;
        this.imageUrl = imageUrl;
    }

    public String getItemNumber() {
        return itemNumber;
    }

    public void setItemNumber(String itemNumber) {
        this.itemNumber = itemNumber;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
