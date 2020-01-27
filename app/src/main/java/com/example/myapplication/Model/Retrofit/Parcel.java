
package com.example.myapplication.Model.Retrofit;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Parcel {

    @SerializedName("category")
    @Expose
    private String category;
    @SerializedName("items")
    @Expose
    private List<Item> items = null;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

}
