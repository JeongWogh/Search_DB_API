package com.example.search_db_api;

import android.content.Context;
import android.content.SharedPreferences;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class RecentSearchesManager {
    private static final String PREF_NAME = "RecentSearches";
    private static final String KEY_RECENT_SEARCHES = "recent_searches";
    private static final int MAX_RECENT_SEARCHES = 5;

    private SharedPreferences sharedPreferences;
    private Gson gson;

    public RecentSearchesManager(Context context) {
        sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        gson = new Gson();
    }

    public void addSearchQuery(String query) {
        List<String> recentSearches = getRecentSearches();

        // 중복 제거
        recentSearches.remove(query);

        // 최근 검색어를 리스트의 첫 번째로 추가
        recentSearches.add(0, query);

        // 최대 개수 유지
        while (recentSearches.size() > MAX_RECENT_SEARCHES) {
            recentSearches.remove(recentSearches.size() - 1);
        }

        // 저장
        saveRecentSearches(recentSearches);
    }

    public List<String> getRecentSearches() {
        String json = sharedPreferences.getString(KEY_RECENT_SEARCHES, null);
        if (json == null) {
            return new ArrayList<>();
        }
        Type type = new TypeToken<List<String>>() {}.getType();
        return gson.fromJson(json, type);
    }

    private void saveRecentSearches(List<String> recentSearches) {
        String json = gson.toJson(recentSearches);
        sharedPreferences.edit().putString(KEY_RECENT_SEARCHES, json).apply();
    }
}