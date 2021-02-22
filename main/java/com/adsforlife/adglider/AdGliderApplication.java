package com.adsforlife.adglider;

import android.app.Application;

public class AdGliderApplication extends Application {
    private static AdGliderApplication adGliderApplication;

    public AdGliderApplication() {
        super();
        adGliderApplication = this;
    }

    public static AdGliderApplication getAdGliderApplication() {
        return adGliderApplication;
    }
}
