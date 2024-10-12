package com.example.search_db_api;

import java.util.ArrayList;
import java.util.List;

public class PillDataStore {

    // PillDataStore의 단일 인스턴스를 저장할 변수 (싱글톤 패턴 적용)
    private static PillDataStore instance;

    // 일반의약품(OTC)과 전문의약품(ETC) 리스트를 별도로 저장할 변수
    private List<Pill> otcPillList;  // 일반의약품 리스트
    private List<Pill> etcPillList;  // 전문의약품 리스트

    // 생성자: 각 리스트를 ArrayList로 초기화
    // 외부에서 객체 생성을 막기 위해 private으로 설정
    private PillDataStore() {
        otcPillList = new ArrayList<>();
        etcPillList = new ArrayList<>();
    }

    // 싱글톤 패턴을 사용하여 PillDataStore의 인스턴스를 반환
    public static synchronized PillDataStore getInstance() {
        // 인스턴스가 없으면 새로 생성하여 반환
        if (instance == null) {
            instance = new PillDataStore();
        }
        return instance;
    }

    // 일반의약품(OTC) 리스트를 반환하는 메서드
    public List<Pill> getOtcPillList() {
        return otcPillList;
    }

    // 전문의약품(ETC) 리스트를 반환하는 메서드
    public List<Pill> getEtcPillList() {
        return etcPillList;
    }

    // 일반의약품(OTC) 리스트를 설정하는 메서드
    public void setOtcPillList(List<Pill> otcPillList) {
        this.otcPillList = otcPillList;
    }

    // 전문의약품(ETC) 리스트를 설정하는 메서드
    public void setEtcPillList(List<Pill> etcPillList) {
        this.etcPillList = etcPillList;
    }

    // 일반의약품 리스트에 약물을 추가하는 메서드
    public void addOtcPill(Pill pill) {
        this.otcPillList.add(pill);
    }

    // 전문의약품 리스트에 약물을 추가하는 메서드
    public void addEtcPill(Pill pill) {
        this.etcPillList.add(pill);
    }

    // 일반의약품 리스트를 초기화하는 메서드
    public void clearOtcPillList() {
        this.otcPillList.clear();
    }

    // 전문의약품 리스트를 초기화하는 메서드
    public void clearEtcPillList() {
        this.etcPillList.clear();
    }
}
