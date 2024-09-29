package com.example.search_db_api;

import android.os.Parcel;
import android.os.Parcelable;

public class Pill implements Parcelable {
    private String itemName;
    private int itemSeq;
    private String efcyQesitm;
    private String atpnQesitm;
    private String seQesitm;
    private String itemImage;
    private String etcotc;

    public Pill() {
    }

    protected Pill(Parcel in) {
        itemName = in.readString();
        itemSeq = in.readInt();
        efcyQesitm = in.readString();
        atpnQesitm = in.readString();
        seQesitm = in.readString();
        itemImage = in.readString();
        etcotc = in.readString();
    }

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
        dest.writeInt(itemSeq);
        dest.writeString(efcyQesitm);
        dest.writeString(atpnQesitm);
        dest.writeString(seQesitm);
        dest.writeString(itemImage);
        dest.writeString(etcotc);
    }

    // Getters and Setters
    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getItemSeq() {
        return itemSeq;
    }

    public void setItemSeq(int itemSeq) {
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
