package com.adsforlife.adglider.Domain;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.adsforlife.adglider.data.localroom.Ad;
import com.adsforlife.adglider.data.localroom.RoomDataSource;
import com.adsforlife.adglider.data.retrofit.RemoteDataSource;
import com.adsforlife.adglider.data.retrofit.User;

import java.util.List;

public class AdGliderMediator {
    private final RoomRepository roomDataSource;
    private final RemoteRepository remoteDataSource;

    protected AdGliderMediator(Application application) {
        roomDataSource = new RoomDataSource(application);
        remoteDataSource = new RemoteDataSource();
    }

    protected void insertAd(Ad ad) {
        roomDataSource.insertAd(ad);
    }

    protected LiveData<List<Ad>> getAllAds() {
        return roomDataSource.getAds();
    }

    protected User getUser(String userEmail) {
        return remoteDataSource.getUser(userEmail);
    }

    protected void setUser(User user) {
        remoteDataSource.updateUser(user);
    }
}
