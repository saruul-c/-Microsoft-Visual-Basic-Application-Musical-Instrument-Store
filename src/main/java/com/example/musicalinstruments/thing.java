package com.example.musicalinstruments;


public class thing {
    private String itemNumber;
    private String itemName;
    private String price;


    public thing(String itemNumber, String itemName, String price) {
        this.itemNumber = itemNumber;
        this.itemName = itemName;
        this.price = price;
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

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }


}

