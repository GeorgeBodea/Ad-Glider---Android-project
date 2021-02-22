package com.adsforlife.adglider.Domain;

import android.app.Application;

import com.adsforlife.adglider.data.retrofit.User;

public class UserUseCase {
    AdGliderMediator adGliderMediator;
    public UserUseCase(Application application) {
        adGliderMediator = new AdGliderMediator(application);
    }

    public User getUser(String userEmail) {
        return adGliderMediator.getUser(userEmail);
    }

    public void setUser(User user) {
        adGliderMediator.setUser(user);
    }

}
