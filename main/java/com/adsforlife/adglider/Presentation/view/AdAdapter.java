package com.adsforlife.adglider.Presentation.view;

import android.content.Context;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;


import com.adsforlife.adglider.Presentation.fragment.AdFragment;
import com.adsforlife.adglider.R;
import com.adsforlife.adglider.data.localroom.Ad;

import java.util.List;

public class AdAdapter extends RecyclerView.Adapter<AdAdapter.AdViewHolder> {

    private List<Ad> ads;
    private Context context;

    public AdAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public AdViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.ad_item, parent, false);
        return new AdViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull AdViewHolder holder, int position) {
        Ad currentAd = ads.get(position);
        holder.getTextViewTitle().setText(currentAd.getTitle());
        holder.getTextViewCompany().setText(currentAd.getCompany());
        holder.getTextViewBody().setText(currentAd.getBody());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("Tag_AD_ADAPTER", "onClick item from recyclerView");
                AppCompatActivity activity = (AppCompatActivity) view.getContext();
                AdFragment frag = new AdFragment();
                activity.getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.thePlaceHolder, frag)
                        .addToBackStack(null).commit();

                final FrameLayout layout = (FrameLayout) activity.findViewById(R.id.thePlaceHolder);

                layout.setVisibility(View.VISIBLE);

                new CountDownTimer(5000, 1000) {

                    @Override
                    public void onTick(long millisUntilFinished) {
                        // do something after 1s
                    }

                    @Override
                    public void onFinish() {
                        layout.setVisibility(View.GONE); // do something end times 5s
                    }

                }.start();

            }
        });


    }

    @Override
    public int getItemCount() {
        if (ads != null)
            return ads.size();
        else
            return 0;
    }

    public void setAds(List<Ad> ads) {
        this.ads = ads;
        notifyDataSetChanged();
    }

    protected class AdViewHolder extends RecyclerView.ViewHolder {

        private TextView textViewTitle, textViewCompany, textViewBody;

        public AdViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewTitle = itemView.findViewById(R.id.adTitleTextView);
            textViewCompany = itemView.findViewById(R.id.adCompanyTextView);
            textViewBody = itemView.findViewById(R.id.adBodyTextView);
        }

        public TextView getTextViewTitle() {
            return textViewTitle;
        }

        public TextView getTextViewCompany() {
            return textViewCompany;
        }

        public TextView getTextViewBody() {
            return textViewBody;
        }

    }
}
