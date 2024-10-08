package com.example.search_db_api;

import android.content.Context;
import android.content.SharedPreferences;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class RecentSearchesManager {

    // SharedPreferences 파일 이름과 검색어를 저장할 키 값
    private static final String PREF_NAME = "RecentSearches";
    private static final String KEY_RECENT_SEARCHES = "recent_searches";
    private static final int MAX_RECENT_SEARCHES = 5;  // 최대 저장할 검색어 수

    private SharedPreferences sharedPreferences;
    private Gson gson;

    // 생성자: SharedPreferences와 Gson 객체 초기화
    public RecentSearchesManager(Context context) {
        sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        gson = new Gson();
    }

    // 새로운 검색어를 추가하는 메서드
    public void addSearchQuery(String query) {
        List<String> recentSearches = getRecentSearches();  // 기존 검색어 리스트를 가져옴

        // 중복된 검색어 제거
        recentSearches.remove(query);

        // 새로운 검색어를 리스트의 맨 앞에 추가
        recentSearches.add(0, query);

        // 최근 검색어 리스트의 최대 개수를 유지 (5개 초과 시 마지막 검색어 제거)
        while (recentSearches.size() > MAX_RECENT_SEARCHES) {
            recentSearches.remove(recentSearches.size() - 1);
        }

        // 업데이트된 리스트를 SharedPreferences에 저장
        saveRecentSearches(recentSearches);
    }

    // 최근 검색어 리스트를 반환하는 메서드
    public List<String> getRecentSearches() {
        // 저장된 검색어 리스트를 JSON 형식으로 가져옴
        String json = sharedPreferences.getString(KEY_RECENT_SEARCHES, null);
        if (json == null) {
            return new ArrayList<>();  // 저장된 값이 없으면 빈 리스트 반환
        }
        // JSON 문자열을 List<String> 형식으로 변환
        Type type = new TypeToken<List<String>>() {}.getType();
        return gson.fromJson(json, type);
    }

    // 최근 검색어 리스트를 SharedPreferences에 저장하는 메서드
    private void saveRecentSearches(List<String> recentSearches) {
        // 리스트를 JSON 형식으로 변환하여 저장
        String json = gson.toJson(recentSearches);
        sharedPreferences.edit().putString(KEY_RECENT_SEARCHES, json).apply();
    }
}
