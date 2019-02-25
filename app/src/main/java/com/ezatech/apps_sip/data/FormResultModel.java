package com.ezatech.apps_sip.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FormResultModel {

    @SerializedName("msg")
    @Expose
    String message;

    public FormResultModel(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
