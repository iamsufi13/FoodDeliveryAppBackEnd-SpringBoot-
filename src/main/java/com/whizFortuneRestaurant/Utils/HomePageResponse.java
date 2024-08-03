package com.whizFortuneRestaurant.Utils;

import java.util.List;

public class HomePageResponse<T> {
    private boolean status;
    private String message;
    private List<T> data;

    public HomePageResponse(boolean status, String message, List<T> data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public boolean       getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}
