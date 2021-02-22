package com.adsforlife.adglider.Domain;

import com.adsforlife.adglider.data.retrofit.User;

public interface RemoteRepository {
    User getUser(String userEmail);

    void updateUser(User userOrice);
}
