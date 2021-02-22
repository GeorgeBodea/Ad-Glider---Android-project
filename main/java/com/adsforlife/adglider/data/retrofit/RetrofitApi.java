package com.adsforlife.adglider.data.retrofit;

import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.google.gson.JsonObject;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface RetrofitApi {
    String BASE_URL = "https://authentication-ad-glider-default-rtdb.firebaseio.com/";

    @GET("users/{id}.json")
    Call<JsonObject> getUser(@Path("id") String id);

    @PUT("users/{id}.json")
    Call<User> updateUser(@Path("id") String id,@Body User userEntity);

    static RetrofitApi createApi() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addNetworkInterceptor(new StethoInterceptor())
                .build();

        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(RetrofitApi.class);
    }
}