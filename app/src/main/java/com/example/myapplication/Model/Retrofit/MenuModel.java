
package com.example.myapplication.Model.Retrofit;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MenuModel {

    @SerializedName("message")
    @Expose
    private String message;

    @SerializedName("data")
    @Expose
    private List<MainMenuModel> data = null;

    @SerializedName("breakfast")
    @Expose
    private int breakfast;

    @SerializedName("lunch")
    @Expose
    private int lunch;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<MainMenuModel> getData() {
        return data;
    }

    public void setData(List<MainMenuModel> data) {
        this.data = data;
    }

    public int getBreakfast() {
        return breakfast;
    }

    public void setBreakfast(int breakfast) {
        this.breakfast = breakfast;
    }

    public int getLunch() {
        return lunch;
    }

    public void setLunch(int lunch) {
        this.lunch = lunch;
    }
}
