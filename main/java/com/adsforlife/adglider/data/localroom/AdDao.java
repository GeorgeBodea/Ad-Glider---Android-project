package com.adsforlife.adglider.data.localroom;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.adsforlife.adglider.data.retrofit.User;

import java.util.List;

@Dao
public interface AdDao {
    @Query("SELECT * from adEntity")
    LiveData<List<Ad>> getAllAdds();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAd(Ad ad);
}