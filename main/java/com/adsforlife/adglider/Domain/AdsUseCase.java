package com.adsforlife.adglider.Domain;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.adsforlife.adglider.data.localroom.Ad;

import java.util.List;
import java.util.Random;

public class AdsUseCase {
    AdGliderMediator adGliderMediator;

    public AdsUseCase(Application application) {
        adGliderMediator = new AdGliderMediator(application);
        Random rand = new Random();
        int co = rand.nextInt(1000000);
        Ad ad = new Ad("Clearly an Ad " + co, "Original", "Buy, consume, repeat");
        adGliderMediator.insertAd(ad);
    }

    public LiveData<List<Ad>> getAllAds() {
        return adGliderMediator.getAllAds();
    }
}
