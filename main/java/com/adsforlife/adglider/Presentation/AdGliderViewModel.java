package com.adsforlife.adglider.Presentation;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.adsforlife.adglider.Domain.AdsUseCase;
import com.adsforlife.adglider.Domain.UserUseCase;
import com.adsforlife.adglider.data.localroom.Ad;
import com.adsforlife.adglider.data.retrofit.User;
import com.google.firebase.auth.FirebaseAuth;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class AdGliderViewModel extends AndroidViewModel {
    private UserUseCase userUseCase;
    private AdsUseCase adsUseCase;
    private static FirebaseAuth mAuth;


    public AdGliderViewModel(@NonNull Application application) {
        super(application);
        this.userUseCase = new UserUseCase(application);
        this.adsUseCase = new AdsUseCase(application);
    }

    public static FirebaseAuth getmAuth() {
        return mAuth;
    }

    public static void setmAuth(FirebaseAuth mAuth) {
        AdGliderViewModel.mAuth = mAuth;
    }

    public User getUser(String userEmail) {
        return userUseCase.getUser(userEmail);
    }

    public void setUser(@NotNull String email, @NotNull double profitAdaugat) {
        User user = new User(email, profitAdaugat);
        userUseCase.setUser(user);
    }

    public LiveData<List<Ad>> getAllAds() {
        return adsUseCase.getAllAds();
    }
}
