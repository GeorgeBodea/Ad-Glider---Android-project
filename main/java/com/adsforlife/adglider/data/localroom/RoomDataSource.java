package com.adsforlife.adglider.data.localroom;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;

import com.adsforlife.adglider.Domain.RoomRepository;
import com.adsforlife.adglider.data.retrofit.User;

import java.util.List;

public class RoomDataSource implements RoomRepository {
    private AdDao adDao;

    public RoomDataSource(Application application) {
        adDao = AppDatabase.getAppDatabase(application).adDao();
    }

    public LiveData<List<Ad>> getAds() {
        return adDao.getAllAdds();
    }

    @Override
    public void insertAd(Ad ad) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            try {
                adDao.insertAd(ad);
            } catch (Exception e) {
                Log.e("Dao", e.toString());
            }
        });
    }
}
