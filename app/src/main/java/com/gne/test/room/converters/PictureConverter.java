package com.gne.test.room.converters;

import androidx.room.TypeConverter;

import com.gne.test.vo.Picture;
import com.google.gson.Gson;

public class PictureConverter {

    @TypeConverter
    public String toJson(Picture picture){
        return new Gson().toJson(picture,Picture.class);
    }

    @TypeConverter
    public Picture fromJson(String string){
        return new Gson().fromJson(string,Picture.class);
    }
}
