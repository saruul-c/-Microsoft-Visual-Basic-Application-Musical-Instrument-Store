package com.example.musicalinstruments;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;

public class StoreItem {
    private SimpleStringProperty category = new SimpleStringProperty();
    private SimpleStringProperty type = new SimpleStringProperty();
    private SimpleStringProperty name = new SimpleStringProperty();
    private SimpleDoubleProperty price = new SimpleDoubleProperty();
    private SimpleStringProperty imageUrl = new SimpleStringProperty();

    public StoreItem(String category, String type, String name, double price, String imageUrl) {
        this.category.set(category);
        this.type.set(type);
        this.name.set(name);
        this.price.set(price);
        this.imageUrl.set(imageUrl);
    }

    // Standard getters for the properties
    public String getCategory() { return category.get(); }
    public String getType() { return type.get(); }
    public String getName() { return name.get(); }
    public double getPrice() { return price.get(); }
    public String getImageUrl() { return imageUrl.get(); }

    // Property getters for JavaFX binding
    public SimpleStringProperty categoryProperty() { return category; }
    public SimpleStringProperty typeProperty() { return type; }
    public SimpleStringProperty nameProperty() { return name; }
    public SimpleDoubleProperty priceProperty() { return price; }
}
