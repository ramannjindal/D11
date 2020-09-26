package com.example.notifications;

import android.graphics.Bitmap;

public class PaymentModel {
    String from;
    String message;
    String time;
public static final String dateFormat="EEE dd MMM 'at' hh:mm a";
    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
