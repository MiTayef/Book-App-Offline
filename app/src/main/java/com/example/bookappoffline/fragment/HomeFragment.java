package com.example.bookappoffline.fragment;

import static androidx.core.content.ContextCompat.getDrawable;

import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;
import android.widget.LinearLayout;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.bookappoffline.AboutActivity;
import com.example.bookappoffline.R;
import com.example.bookappoffline.helper.MyMethod;
import com.example.bookappoffline.model.CategoryAdapter;
import com.example.bookappoffline.model.CategoryItem;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    List<CategoryItem> categoryItemList;
    CategoryAdapter categoryAdapter;
    GridView cat_gridView;
    ImageSlider image_slider;
    private  LinearLayout adContainerView;
    Dialog dialog;


    //:::::::::::::::For Toolbar and Navigation Menu
    Toolbar toolbar;
    NavigationView nav_View;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle toggle;
    //::::::::::::::::::::::::::::::::

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        toolbar = view.findViewById(R.id.toolbar);
        cat_gridView = view.findViewById(R.id.cat_gridView);
        drawerLayout = view.findViewById(R.id.drawerLayout);
        image_slider = view.findViewById(R.id.image_slider);
        nav_View = view.findViewById(R.id.nav_View);
        adContainerView = view.findViewById(R.id.adContainerView);

        // Initialize the Google Mobile Ads SDK on a background thread.
        new Thread(
                () -> {
                    MobileAds.initialize(requireContext(), initializationStatus -> {});
                })
                .start();


        // Set up toolbar and navigation menu
        toolbar = view.findViewById(R.id.toolbar);
        nav_View = view.findViewById(R.id.nav_View);
        drawerLayout = view.findViewById(R.id.drawerLayout);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);


        // Create a new ad view.
        AdView adView = new AdView(requireContext());
        adView.setAdUnitId("ca-app-pub-3940256099942544/9214589741");
        adView.setAdSize(AdSize.BANNER);

        // Replace ad container with new ad view.
        adContainerView.removeAllViews();
        adContainerView.addView(adView);

        // Start loading the ad in the background.
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);



        // Set up image slider
        ArrayList<SlideModel> imageList = new ArrayList<>();
        imageList.add(new SlideModel(R.drawable.image_slider_1, ScaleTypes.CENTER_CROP));
        imageList.add(new SlideModel(R.drawable.image_slider_2, ScaleTypes.CENTER_CROP));
        imageList.add(new SlideModel(R.drawable.image_slider_3, ScaleTypes.CENTER_CROP));
        imageList.add(new SlideModel(R.drawable.image_slider_4, ScaleTypes.CENTER_CROP));
        imageList.add(new SlideModel(R.drawable.image_slider_5, ScaleTypes.CENTER_CROP));
        imageList.add(new SlideModel(R.drawable.image_slider_6, ScaleTypes.CENTER_CROP));
        image_slider.setImageList(imageList);

        // Populate the GridView with items
        categoryItemList = new ArrayList<>();
        categoryItemList.add(new CategoryItem(R.drawable.nazrur, "কাজী নজরুল ইসলাম", "বিদ্রোহী কবি", 1));
        categoryItemList.add(new CategoryItem(R.drawable.ravindra, "রবীন্দ্রনাথ ঠাকুর", "বিশ্ব কবি", 1));
        categoryItemList.add(new CategoryItem(R.drawable.humayon, "হুমায়ূন আহমেদ", "উপন্যাসক", 1));
        categoryItemList.add(new CategoryItem(R.drawable.jibanananda, "জীবনানন্দ দাশ", "কবি", 1));
        categoryItemList.add(new CategoryItem(R.drawable.samsur, "শামসুর রাহমান", "কবি", 1));
        categoryItemList.add(new CategoryItem(R.drawable.akhtar, "আখতারুজ্জামান", "উপন্যাসক ও গল্পকার", 1));

        categoryAdapter = new CategoryAdapter(categoryItemList, getContext());
        cat_gridView.setAdapter(categoryAdapter);


        // Set up exit app feature
        requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(), new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                showExitConfirmationDialog();
            }
        });


        // Set up drawer toggle
        toggle = new ActionBarDrawerToggle(getActivity(), drawerLayout, toolbar, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        // Handle navigation menu item clicks
        nav_View.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.mHome:
                        drawerLayout.closeDrawers();
                        break;

                    case R.id.mBookReq:
                        MyMethod.openBrowser(getContext(), "https://forms.gle/zE9c7Eg7uUZWHbyW6");
                        drawerLayout.closeDrawers();
                        break;

                    case R.id.mShare:
                        MyMethod.shareApp(getContext());
                        drawerLayout.closeDrawers();
                        break;

                    case R.id.mRate:
                        final String appPackageName = getActivity().getPackageName();
                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)));
                        drawerLayout.closeDrawers();
                        break;

                    case R.id.mPrivacy:
                        MyMethod.openBrowser(getContext(), "https://primecompanybangla.blogspot.com/2024/09/book-app-privacy-policy.html");
                        drawerLayout.closeDrawers();
                        break;

                    case R.id.mAbout:
                        startActivity(new Intent(getActivity(), AboutActivity.class));
                        drawerLayout.closeDrawers();
                        break;
                }

                drawerLayout.closeDrawer(GravityCompat.START);
                return true;  // Make sure this returns true
            }
        });

        return view;
    } // onCreate Method End Here

    // Show exit confirmation dialog
    private void showExitConfirmationDialog() {
        dialog = new Dialog(getContext());
        dialog.setContentView(R.layout.custom_alert_dialouge);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawable(getDrawable(getContext(), R.drawable.alert_dialouge_bg));

        Button yesButton = dialog.findViewById(R.id.yesButton);
        Button noButton = dialog.findViewById(R.id.noButton);

        yesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requireActivity().finish();
            }
        });
        noButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }


} // Public Class End Here
