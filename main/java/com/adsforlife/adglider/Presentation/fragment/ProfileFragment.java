package com.adsforlife.adglider.Presentation.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.adsforlife.adglider.AdGliderApplication;
import com.adsforlife.adglider.Presentation.AdGliderViewModel;
import com.adsforlife.adglider.Presentation.AdGliderViewModelFactory;
import com.adsforlife.adglider.R;
import com.adsforlife.adglider.data.retrofit.User;

import java.util.Objects;

public class ProfileFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextView textView = requireView().findViewById(R.id.balance);
        AdGliderViewModel adGliderViewModel = new ViewModelProvider(this, new AdGliderViewModelFactory(AdGliderApplication.getAdGliderApplication())).get(AdGliderViewModel.class);
        User user = adGliderViewModel.getUser(Objects.requireNonNull(AdGliderViewModel.getmAuth().getCurrentUser()).getEmail());
        textView.setText(String.valueOf(user.getProfit()));
    }
}
