package com.gne.test.vo;

import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.databinding.BindingAdapter;
import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.gne.test.R;
import com.gne.test.room.converters.DobConverter;
import com.gne.test.room.converters.LocationConverter;
import com.gne.test.room.converters.LoginConverter;
import com.gne.test.room.converters.NameConverter;
import com.gne.test.room.converters.PictureConverter;
import com.squareup.picasso.Picasso;

@Entity
public class User {
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id")
    @NonNull
    public String email ="";

    public String gender="";
    @Embedded
    @TypeConverters(NameConverter.class)
    public Name name;
    @Embedded
    @TypeConverters(LocationConverter.class)
    public Location location;
    @Embedded
    @TypeConverters(DobConverter.class)
    public Dob dob;
    @Embedded
    @TypeConverters(LoginConverter.class)
    public Login login;

    public boolean isLiked, isDisliked;

    @Embedded
    @TypeConverters(PictureConverter.class)
    public Picture picture;

    public String getGender() {
        return gender;
    }

    public Name getName() {
        return name;
    }

    public Location getLocation() {
        return location;
    }

    public Dob getDob() {
        return dob;
    }

    public Login getLogin() {
        return login;
    }

    @NonNull
    public String getEmail() {
        return email;
    }

    public boolean isLiked() {
        return isLiked;
    }

    public boolean isDisliked() {
        return isDisliked;
    }

    public Picture getPicture() {
        return picture;
    }

    @BindingAdapter({"setImage"})
    public static void setImage(ImageView imageView, String imgUrl){
        Picasso.get().load(imgUrl).placeholder(R.drawable.ic_person_48dp).into(imageView);
    }
}