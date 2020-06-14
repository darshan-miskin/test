package com.gne.test.room.converters;

import androidx.room.TypeConverter;

import com.gne.test.vo.Name;
import com.gne.test.vo.User;
import com.google.gson.Gson;

public class NameConverter {

    @TypeConverter
    public String toJson(Name name){
        return new Gson().toJson(name,Name.class);
    }

    @TypeConverter
    public Name fromJson(String string){
        return new Gson().fromJson(string,Name.class);
    }
}
