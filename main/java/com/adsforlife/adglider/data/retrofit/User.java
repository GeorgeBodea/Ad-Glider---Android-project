package com.adsforlife.adglider.data.retrofit;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.jetbrains.annotations.NotNull;


public class User {
    @NotNull
    @Expose
    @SerializedName("email")
    private String email;

    @Expose
    @SerializedName("profit")
    private double profit;

    public User(@NotNull String email, double profit) {
        this.email = email;
        this.profit = profit;
    }

    @NotNull
    public String getEmail() {
        return email;
    }


    public void setEmail(@NotNull String email) {
        this.email = email;
    }

    public double getProfit() {
        return profit;
    }

    public void setProfit(double profit) {
        this.profit = profit;
    }

    @NotNull
    @Override
    public String toString() {
        return "User{" +
                "email='" + email + '\'' +
                ", profit='" + profit + '\'' +
                '}';
    }
}