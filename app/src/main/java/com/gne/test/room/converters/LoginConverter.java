package com.gne.test.room.converters;

import androidx.room.TypeConverter;

import com.gne.test.vo.Login;
import com.gne.test.vo.User;
import com.google.gson.Gson;

public class LoginConverter {

    @TypeConverter
    public String toJson(Login login){
        return new Gson().toJson(login,Login.class);
    }

    @TypeConverter
    public Login fromJson(String string){
        return new Gson().fromJson(string,Login.class);
    }
}
