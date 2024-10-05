package com.example.search_db_api;

import java.util.ArrayList;
import java.util.List;

public class PillDataStore {
    // PillDataStore의 단일 인스턴스를 저장할 변수
    private static PillDataStore instance;
    // Pill 객체를 저장할 리스트
    private List<Pill> pillList;

    // 생성자: pillList를 초기화
    private PillDataStore() {
        pillList = new ArrayList<>();
    }

    // 싱글톤 패턴을 사용하여 PillDataStore의 인스턴스를 반환
    public static synchronized PillDataStore getInstance() {
        // 인스턴스가 없으면 새로 생성
        if (instance == null) {
            instance = new PillDataStore();
        }
        return instance;
    }

    // pillList를 반환하는 메서드
    public List<Pill> getPillList() {
        return pillList;
    }

    // pillList를 설정하는 메서드
    public void setPillList(List<Pill> pillList) {
        this.pillList = pillList;
    }
}
