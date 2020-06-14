package com.gne.test.vo;

import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

public class Login {
    public String uuid="", username="", salt="", md5="", sha1="", sha256="";

    @PrimaryKey()
    @SerializedName("id")
    public String getUuid() {
        return uuid;
    }

    public String getUsername() {
        return username;
    }

    public String getSalt() {
        return salt;
    }

    public String getMd5() {
        return md5;
    }

    public String getSha1() {
        return sha1;
    }

    public String getSha256() {
        return sha256;
    }
}
