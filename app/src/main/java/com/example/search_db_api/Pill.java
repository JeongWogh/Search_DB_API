package com.example.search_db_api;

import android.os.Parcel;
import android.os.Parcelable;

public class Pill implements Parcelable {
    private String itemName;
    private String itemSeq; // itemSeq를 String 타입으로 변경
    private String efcyQesitm;
    private String atpnQesitm;
    private String seQesitm;
    private String itemImage;
    private String etcotc;

    // 기본 생성자
    public Pill() {
    }

    // Parcelable 인터페이스를 위한 생성자
    protected Pill(Parcel in) {
        itemName = in.readString();
        itemSeq = in.readString(); // String으로 변경
        efcyQesitm = in.readString();
        atpnQesitm = in.readString();
        seQesitm = in.readString();
        itemImage = in.readString();
        etcotc = in.readString();
    }

    // Parcelable 인터페이스 메소드
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(itemName);
        dest.writeString(itemSeq); // String으로 변경
        dest.writeString(efcyQesitm);
        dest.writeString(atpnQesitm);
        dest.writeString(seQesitm);
        dest.writeString(itemImage);
        dest.writeString(etcotc);
    }

    // Getter 및 Setter 메소드
    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemSeq() {
        return itemSeq;
    }

    public void setItemSeq(String itemSeq) {
        this.itemSeq = itemSeq;
    }

    public String getEfcyQesitm() {
        return efcyQesitm;
    }

    public void setEfcyQesitm(String efcyQesitm) {
        this.efcyQesitm = efcyQesitm;
    }

    public String getAtpnQesitm() {
        return atpnQesitm;
    }

    public void setAtpnQesitm(String atpnQesitm) {
        this.atpnQesitm = atpnQesitm;
    }

    public String getSeQesitm() {
        return seQesitm;
    }

    public void setSeQesitm(String seQesitm) {
        this.seQesitm = seQesitm;
    }

    public String getItemImage() {
        return itemImage;
    }

    public void setItemImage(String itemImage) {
        this.itemImage = itemImage;
    }

    public String getEtcotc() {
        return etcotc;
    }

    public void setEtcotc(String etcotc) {
        this.etcotc = etcotc;
    }
}
