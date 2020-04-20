package com.pratech.SpringFireStoreservice.model;

import java.util.List;

public class Collection {
    private List<Item> items;
    private  String title;

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
