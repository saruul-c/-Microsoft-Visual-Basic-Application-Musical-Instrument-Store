package com.example.musicalinstruments;

public class AvailableItems {

    private Number colItemNumber;
    private String colItemName;
    private String colUnitPrice;


    public AvailableItems(Number colItemNumber, String colItemName, String colUnitPrice){
        this.colItemNumber = colItemNumber;
        this.colItemName = colItemName;
        this.colUnitPrice = colUnitPrice;
    }

    public Number getColItemNumber() {
        return colItemNumber;
    }

    public void setColItemNumber(Number colItemNumber) {
        this.colItemNumber = colItemNumber;
    }

    public String getColItemName() {
        return colItemName;
    }

    public void setColItemName(String colItemName) {
        this.colItemName = colItemName;
    }

    public String getColUnitPrice() {
        return colUnitPrice;
    }

    public void setColUnitPrice(String colUnitPrice) {
        this.colUnitPrice = colUnitPrice;
    }
}
