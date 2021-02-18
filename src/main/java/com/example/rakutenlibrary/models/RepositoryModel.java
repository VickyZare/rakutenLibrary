package com.example.rakutenlibrary.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class RepositoryModel implements Parcelable {

    @SerializedName("total_count")
    private int total_count;

    @SerializedName("incomplete_results")
    private boolean incomplete_results;

    @SerializedName("items")
    private ArrayList<RepositoryInfo> items;

    public RepositoryModel() {
    }

    protected RepositoryModel(Parcel in) {
        total_count = in.readInt();
        incomplete_results = in.readByte() != 0;
        items = in.createTypedArrayList(RepositoryInfo.CREATOR);
    }

    public static final Creator<RepositoryModel> CREATOR = new Creator<RepositoryModel>() {
        @Override
        public RepositoryModel createFromParcel(Parcel in) {
            return new RepositoryModel(in);
        }

        @Override
        public RepositoryModel[] newArray(int size) {
            return new RepositoryModel[size];
        }
    };

    public int getTotal_count() {
        return total_count;
    }

    public void setTotal_count(int total_count) {
        this.total_count = total_count;
    }

    public boolean isIncomplete_results() {
        return incomplete_results;
    }

    public void setIncomplete_results(boolean incomplete_results) {
        this.incomplete_results = incomplete_results;
    }

    public ArrayList<RepositoryInfo> getItems() {
        return items;
    }

    public void setItems(ArrayList<RepositoryInfo> items) {
        this.items = items;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(total_count);
        parcel.writeByte((byte) (incomplete_results ? 1 : 0));
        parcel.writeTypedList(items);
    }
}
