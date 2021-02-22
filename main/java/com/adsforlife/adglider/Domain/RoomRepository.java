package com.adsforlife.adglider.Domain;

import androidx.lifecycle.LiveData;

import com.adsforlife.adglider.data.localroom.Ad;

import java.util.List;

public interface RoomRepository {
    void insertAd(Ad ad);

    LiveData<List<Ad>> getAds();
}
