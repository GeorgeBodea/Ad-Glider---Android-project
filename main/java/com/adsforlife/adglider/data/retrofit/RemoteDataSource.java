package com.adsforlife.adglider.data.retrofit;

import android.os.StrictMode;
import android.util.Log;

import com.adsforlife.adglider.Domain.RemoteRepository;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.jetbrains.annotations.NotNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RemoteDataSource implements RemoteRepository {
    private final RetrofitApi api = RetrofitApi.createApi();

    @Override
    public User getUser(String userEmail) {
        User userEntity;
        try {
            Gson gson = new Gson();
            StrictMode.setThreadPolicy(StrictMode.ThreadPolicy.LAX);
            JsonObject response = api.getUser(userEmail.replace('.', ',')).execute().body();
            userEntity = gson.fromJson(response, User.class);
        } catch (Exception e) {
            userEntity = new User(userEmail, 0.0);
            Log.d("DataSource", "Eroare Get all Users");
        }
        return userEntity;
    }

    @Override
    public void updateUser(User userOrice) {
        try {
            Gson gson = new Gson();
            StrictMode.setThreadPolicy(StrictMode.ThreadPolicy.LAX);
            JsonObject response = api.getUser(userOrice.getEmail().replace('.', ',')).execute().body();
            if (response != null) {
                User user = gson.fromJson(response, User.class);

                if (user != null) {
                    userOrice.setProfit(userOrice.getProfit() + user.getProfit());
                }
            }
        } catch (Exception e) {
            Log.d("firebase post", e.toString());
        }
        try {
            Call<User> call = api.updateUser(userOrice.getEmail().replace('.', ','), userOrice);

            call.enqueue(new Callback<User>() {
                @Override
                public void onResponse(@NotNull Call<User> call, @NotNull Response<User> response) {
                    Log.d("RemoteDataS", "Success inserting user in firebase db");
                }

                @Override
                public void onFailure(@NotNull Call<User> call, @NotNull Throwable t) {
                    Log.d("RemoteDataS", "fail inserting user in firebase db");
                }
            });


        } catch (
                Exception e) {
            Log.d("RemoteDataSource", "Eroare Get all Users");
        }

    }
}