package com.example.bookappoffline.model;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookappoffline.PdfDetails;
import com.example.bookappoffline.R;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.google.android.gms.ads.LoadAdError;

import java.util.List;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.birdViewHolder> {

    List<BookItem> itemList;
    Context context;
    private InterstitialAd interstitialAd;

    public BookAdapter(List<BookItem> itemList, Context context) {
        this.itemList = itemList;
        this.context = context;

        // Initialize the Mobile Ads SDK
        MobileAds.initialize(context, initializationStatus -> {});

        // Load the first interstitial ad
        loadInterstitialAd();
    }

    private void loadInterstitialAd() {
        AdRequest adRequest = new AdRequest.Builder().build();
        InterstitialAd.load(context, "ca-app-pub-7795414021148606/2111923717", adRequest, new InterstitialAdLoadCallback() {
            @Override
            public void onAdLoaded(@NonNull InterstitialAd ad) {
                // Ad successfully loaded
                interstitialAd = ad;
            }

            @Override
            public void onAdFailedToLoad(@NonNull LoadAdError adError) {
                // Handle the error, and set the interstitialAd to null
                interstitialAd = null;
            }
        });
    }

    @NonNull
    @Override
    public BookAdapter.birdViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new birdViewHolder(LayoutInflater.from(context).inflate(R.layout.book_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull BookAdapter.birdViewHolder holder, int position) {
        BookItem bookItem = itemList.get(position);
        int itemNumber = position + 1;
        holder.bird_item_number.setText("" + itemNumber);
        holder.setData(bookItem); // Passing BookItem object to setData method
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public class birdViewHolder extends RecyclerView.ViewHolder {

        TextView bird_item_number, bird_title, bird_sub_title;

        public birdViewHolder(@NonNull View itemView) {
            super(itemView);

            bird_item_number = itemView.findViewById(R.id.bird_item_number);
            bird_title = itemView.findViewById(R.id.bird_title);
            bird_sub_title = itemView.findViewById(R.id.bird_sub_title);
        }

        void setData(BookItem bookItem) {
            bird_title.setText(bookItem.getTitle());
            bird_sub_title.setText(bookItem.getSubTitle());

            itemView.setOnClickListener(view -> {
                if (interstitialAd != null) {
                    // Show the ad before moving to the PdfDetails activity
                    interstitialAd.show((android.app.Activity) context);
                    // Set a listener to detect when the ad is closed
                    interstitialAd.setFullScreenContentCallback(new com.google.android.gms.ads.FullScreenContentCallback() {
                        @Override
                        public void onAdDismissedFullScreenContent() {
                            // Ad is dismissed, move to PdfDetails
                            moveToPdfDetails(bookItem, view);
                            // Load a new ad for the next time
                            loadInterstitialAd();
                        }

                        @Override
                        public void onAdFailedToShowFullScreenContent(com.google.android.gms.ads.AdError adError) {
                            // If the ad fails to show, move to PdfDetails directly
                            moveToPdfDetails(bookItem, view);
                        }

                        @Override
                        public void onAdShowedFullScreenContent() {
                            // Set interstitialAd to null after showing to avoid reusing
                            interstitialAd = null;
                        }
                    });
                } else {
                    // If the ad is not loaded, proceed directly to PdfDetails activity
                    moveToPdfDetails(bookItem, view);
                }
            });
        }

        private void moveToPdfDetails(BookItem bookItem, View view) {
            Intent intent = new Intent(view.getContext(), PdfDetails.class);
            intent.putExtra("title", bookItem.getTitle());
            intent.putExtra("pdfFileName", bookItem.getPdfFileName());
            view.getContext().startActivity(intent);
        }
    }
}
