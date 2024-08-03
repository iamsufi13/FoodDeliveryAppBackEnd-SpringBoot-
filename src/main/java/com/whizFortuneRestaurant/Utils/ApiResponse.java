package com.whizFortuneRestaurant.Utils;

import java.util.List;

public class ApiResponse<T> {
    private boolean status;
    private String messsage;
    private List<T> response_data;

    public ApiResponse(boolean status, String messsage, Object response_data) {
        this.status = status;
        this.messsage = messsage;
        this.response_data = (List<T>) response_data;
    }


    public String getMessage() {
        return messsage;
    }

    public void setMessage(String message) {
        this.messsage = message;
    }

    public List<T> getData() {
        return response_data;
    }

    public void setData(List<T> data) {
        this.response_data = response_data;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}