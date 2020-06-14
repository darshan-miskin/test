package com.gne.test.room;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.gne.test.vo.Result;
import com.gne.test.vo.User;

import java.util.List;

@Dao
public interface DaoInterface {
    @Query("DELETE FROM User")
    void deleteAll();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertAllUsers(List<User> users);

    @Query("SELECT * FROM User LIMIT 10 OFFSET :count")
    List<User> fetchUsers(int count);

    @Query("UPDATE User SET isLiked = :isLiked WHERE id = :email")
    int updateLike(boolean isLiked, String email);

    @Query("UPDATE User SET isDisliked = :isDisliked WHERE id = :email")
    int updateDisLike(boolean isDisliked, String email);
}
