package com.example.search_db_api;

import java.util.List;

public class PillResponse {
    private boolean success;
    private String message;
    private List<Pill> data; // 약물 목록

    // 게터와 세터
    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Pill> getData() {
        return data;
    }

    public void setData(List<Pill> data) {
        this.data = data;
    }
}
