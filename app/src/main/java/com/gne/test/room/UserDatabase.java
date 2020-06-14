package com.gne.test.room;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.gne.test.vo.User;

@Database(entities = {User.class}, version = 1, exportSchema = false)
public abstract class UserDatabase extends RoomDatabase {
    abstract DaoInterface dao();
}
