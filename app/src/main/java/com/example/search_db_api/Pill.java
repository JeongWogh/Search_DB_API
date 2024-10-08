package com.example.search_db_api;

import android.os.Parcel;
import android.os.Parcelable;

public class Pill implements Parcelable {

    // 약물의 이름
    private String itemName;

    // 약물의 일련번호 (String 타입)
    private String itemSeq;

    // 약물의 효능 정보
    private String efcyQesitm;

    // 약물의 주의사항 정보
    private String atpnQesitm;

    // 약물의 부작용 정보
    private String seQesitm;

    // 약물의 이미지 URL
    private String itemImage;

    // 약물의 기타 분류 정보
    private String etcotc;

    // 기본 생성자
    public Pill() {
    }

    // Parcelable 인터페이스를 위한 생성자
    protected Pill(Parcel in) {
        itemName = in.readString();
        itemSeq = in.readString(); // itemSeq를 String으로 변경
        efcyQesitm = in.readString();
        atpnQesitm = in.readString();
        seQesitm = in.readString();
        itemImage = in.readString();
        etcotc = in.readString();
    }

    // Parcelable 인터페이스를 구현하는 메서드: 객체를 Parcel에서 생성
    public static final Creator<Pill> CREATOR = new Creator<Pill>() {
        @Override
        public Pill createFromParcel(Parcel in) {
            return new Pill(in);
        }

        @Override
        public Pill[] newArray(int size) {
            return new Pill[size];
        }
    };

    // Parcelable 인터페이스 메서드: Parcel에 작성된 객체의 설명
    @Override
    public int describeContents() {
        return 0;
    }

    // 객체를 Parcel로 직렬화하는 메서드
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(itemName);
        dest.writeString(itemSeq); // itemSeq를 String으로 변경
        dest.writeString(efcyQesitm);
        dest.writeString(atpnQesitm);
        dest.writeString(seQesitm);
        dest.writeString(itemImage);
        dest.writeString(etcotc);
    }

    // Getter 및 Setter 메서드: 각 필드의 값을 가져오거나 설정

    // 약물의 이름을 반환하는 메서드
    public String getItemName() {
        return itemName;
    }

    // 약물의 이름을 설정하는 메서드
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    // 약물의 일련번호를 반환하는 메서드
    public String getItemSeq() {
        return itemSeq;
    }

    // 약물의 일련번호를 설정하는 메서드
    public void setItemSeq(String itemSeq) {
        this.itemSeq = itemSeq;
    }

    // 약물의 효능 정보를 반환하는 메서드
    public String getEfcyQesitm() {
        return efcyQesitm;
    }

    // 약물의 효능 정보를 설정하는 메서드
    public void setEfcyQesitm(String efcyQesitm) {
        this.efcyQesitm = efcyQesitm;
    }

    // 약물의 주의사항 정보를 반환하는 메서드
    public String getAtpnQesitm() {
        return atpnQesitm;
    }

    // 약물의 주의사항 정보를 설정하는 메서드
    public void setAtpnQesitm(String atpnQesitm) {
        this.atpnQesitm = atpnQesitm;
    }

    // 약물의 부작용 정보를 반환하는 메서드
    public String getSeQesitm() {
        return seQesitm;
    }

    // 약물의 부작용 정보를 설정하는 메서드
    public void setSeQesitm(String seQesitm) {
        this.seQesitm = seQesitm;
    }

    // 약물의 이미지 URL을 반환하는 메서드
    public String getItemImage() {
        return itemImage;
    }

    // 약물의 이미지 URL을 설정하는 메서드
    public void setItemImage(String itemImage) {
        this.itemImage = itemImage;
    }

    // 약물의 기타 분류 정보를 반환하는 메서드
    public String getEtcotc() {
        return etcotc;
    }

    // 약물의 기타 분류 정보를 설정하는 메서드
    public void setEtcotc(String etcotc) {
        this.etcotc = etcotc;
    }
}
