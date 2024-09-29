package com.example.search_db_api;

import java.util.ArrayList;
import java.util.List;

public class PillDataStore {
    private static PillDataStore instance;
    private List<Pill> pillList;

    private PillDataStore() {
        pillList = new ArrayList<>();
    }

    public static synchronized PillDataStore getInstance() {
        if (instance == null) {
            instance = new PillDataStore();
        }
        return instance;
    }

    public List<Pill> getPillList() {
        return pillList;
    }

    public void setPillList(List<Pill> pillList) {
        this.pillList = pillList;
    }
}
