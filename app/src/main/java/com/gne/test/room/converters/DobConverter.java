package com.gne.test.room.converters;

import androidx.room.TypeConverter;

import com.gne.test.vo.Dob;
import com.gne.test.vo.User;
import com.google.gson.Gson;

public class DobConverter {

    @TypeConverter
    public String toJson(Dob dob){
        return new Gson().toJson(dob,Dob.class);
    }

    @TypeConverter
    public Dob fromJson(String string){
        return new Gson().fromJson(string,Dob.class);
    }
}
