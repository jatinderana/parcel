
package com.example.myapplication.Model.Retrofit;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class homemenumodel {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("data")
    @Expose
    private List<homemenu> data = null;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<homemenu> getData() {
        return data;
    }

    public void setData(List<homemenu> data) {
        this.data = data;
    }

}
