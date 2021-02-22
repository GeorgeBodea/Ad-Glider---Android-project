package com.adsforlife.adglider.Presentation.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.adsforlife.adglider.AdGliderApplication;
import com.adsforlife.adglider.Presentation.AdGliderViewModel;
import com.adsforlife.adglider.Presentation.AdGliderViewModelFactory;
import com.adsforlife.adglider.R;

import java.util.Objects;

public class AdFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ad, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        AdGliderViewModel adGliderViewModel = new ViewModelProvider(this, new AdGliderViewModelFactory(AdGliderApplication.getAdGliderApplication())).get(AdGliderViewModel.class);
        adGliderViewModel.setUser(Objects.requireNonNull(Objects.requireNonNull(AdGliderViewModel.getmAuth().getCurrentUser()).getEmail()), 0.10);
    }
}