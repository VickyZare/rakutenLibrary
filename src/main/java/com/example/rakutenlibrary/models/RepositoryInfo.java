package com.example.rakutenlibrary.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class RepositoryInfo implements Parcelable {

    @SerializedName("id")
    private long id;

    @SerializedName("name")
    private String name;

    @SerializedName("private")
    private boolean isPrivate;

    @SerializedName("description")
    private String description;

    @SerializedName("language")
    private String language;


    public RepositoryInfo() {
    }


    protected RepositoryInfo(Parcel in) {
        id = in.readLong();
        name = in.readString();
        isPrivate = in.readByte() != 0;
        description = in.readString();
        language = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeString(name);
        dest.writeByte((byte) (isPrivate ? 1 : 0));
        dest.writeString(description);
        dest.writeString(language);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<RepositoryInfo> CREATOR = new Creator<RepositoryInfo>() {
        @Override
        public RepositoryInfo createFromParcel(Parcel in) {
            return new RepositoryInfo(in);
        }

        @Override
        public RepositoryInfo[] newArray(int size) {
            return new RepositoryInfo[size];
        }
    };

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isPrivate() {
        return isPrivate;
    }

    public void setPrivate(boolean aPrivate) {
        isPrivate = aPrivate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

}
