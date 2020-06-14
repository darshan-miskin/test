package com.gne.test.room.converters;

import androidx.room.TypeConverter;

import com.gne.test.vo.Location;
import com.gne.test.vo.User;
import com.google.gson.Gson;

public class LocationConverter {

    @TypeConverter
    public String toJson(Location location){
        return new Gson().toJson(location,Location.class);
    }

    @TypeConverter
    public Location fromJson(String string){
        return new Gson().fromJson(string,Location.class);
    }
}
