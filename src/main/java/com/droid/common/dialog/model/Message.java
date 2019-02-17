package com.droid.common.dialog.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by sohan.gupta on 17/2/19.
 */

public class Message implements Parcelable {
    private String imageUrl;
    private String text;

    public Message(){
    }

    protected Message(Parcel in) {
        imageUrl = in.readString();
        text = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(imageUrl);
        dest.writeString(text);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Message> CREATOR = new Creator<Message>() {
        @Override
        public Message createFromParcel(Parcel in) {
            return new Message(in);
        }

        @Override
        public Message[] newArray(int size) {
            return new Message[size];
        }
    };

    public String getImageUrl() {
        return imageUrl;
    }

    public String getText() {
        return text;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setText(String text) {
        this.text = text;
    }
}

