package com.adsforlife.adglider.Presentation;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class AdGliderViewModelFactory implements ViewModelProvider.Factory {
    private final Application application;

    public AdGliderViewModelFactory( Application application ) {
        this.application = application;
    }

    @NonNull
    @Override
    public < T extends ViewModel> T create(@NonNull Class < T > modelClass ) {
        return modelClass.cast( new AdGliderViewModel( application ) );
    }
}