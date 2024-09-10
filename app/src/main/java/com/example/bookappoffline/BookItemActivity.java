package com.example.bookappoffline;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.bookappoffline.model.BookAdapter;
import com.example.bookappoffline.model.BookItem;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;

import java.util.ArrayList;
import java.util.List;

public class BookItemActivity extends AppCompatActivity {

    RecyclerView birdRecyclerView;
    List<BookItem> bookItemList = new ArrayList<>();
    BookAdapter bookAdapter;
    private LinearLayout adContainerView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_item);

        // Retrieve data passed from CategoryAdapter
        Bundle bundle = getIntent().getExtras();
        String title = bundle != null ? bundle.getString("title") : "Unknown Category";
        TextView birdItemTitle = findViewById(R.id.birdItemTitle);
        birdItemTitle.setText(title);

        // Toolbar back button setup
        androidx.appcompat.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        birdRecyclerView = findViewById(R.id.birdRecyclerView);
        adContainerView2 = findViewById(R.id.adContainerView2);



        // Create a new ad view.
        AdView adView = new AdView(BookItemActivity.this);
        adView.setAdUnitId("ca-app-pub-7795414021148606/3713328734");
        adView.setAdSize(AdSize.BANNER);

        // Replace ad container with new ad view.
        adContainerView2.removeAllViews();
        adContainerView2.addView(adView);

        // Start loading the ad in the background.
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);

        birdRecyclerView.setLayoutManager(new LinearLayoutManager(BookItemActivity.this, RecyclerView.VERTICAL, false));

        bookAdapter = new BookAdapter(bookItemList, BookItemActivity.this);
        birdRecyclerView.setAdapter(bookAdapter);

        // Populate bookItemList based on the category title
        populateBirdItems(title);


    } // onCreate End Here

    private void populateBirdItems(String categoryTitle) {

        switch (categoryTitle) {
            case "কাজী নজরুল ইসলাম":
                bookItemList.add(new BookItem("বিদ্রোহী", "কবিতা", "book.pdf"));
                bookItemList.add(new BookItem("মোহররম", "কবিতা", "book.pdf"));
                bookItemList.add(new BookItem("আগমনী", "কবিতা", "book.pdf"));
                bookItemList.add(new BookItem("কামাল পাশা", "কবিতা", "book.pdf"));
                bookItemList.add(new BookItem("খুকি ও কাঠবিড়ালী", "কবিতা", "book.pdf"));
                bookItemList.add(new BookItem("দারিদ্র্য", "কবিতা", "book.pdf"));
                bookItemList.add(new BookItem("আনন্দময়ীর আগমনে", "কবিতা", "book.pdf"));
                bookItemList.add(new BookItem("ভাঙার গান", "কবিতা", "book.pdf"));
                bookItemList.add(new BookItem("প্রলয়োল্লাস", "কবিতা", "book.pdf"));
                bookItemList.add(new BookItem("নারী", "কবিতা", "book.pdf"));
                break;

            case "রবীন্দ্রনাথ ঠাকুর":
                bookItemList.add(new BookItem("গীতাঞ্জলি", "কবিতা", "book.pdf"));
                bookItemList.add(new BookItem("ঘরে বাইরে", "উপন্যাস", "book.pdf"));
                bookItemList.add(new BookItem("গোরা", "উপন্যাস", "book.pdf"));
                bookItemList.add(new BookItem("চোখের বালি", "উপন্যাস", "book.pdf"));
                bookItemList.add(new BookItem("শেষের কবিতা", "উপন্যাস", "book.pdf"));
                bookItemList.add(new BookItem("কালান্তর", "প্রবন্ধ", "book.pdf"));
                break;

            case "হুমায়ূন আহমেদ":
                bookItemList.add(new BookItem("নন্দিত নরকে", "উপন্যাস", "book.pdf"));
                bookItemList.add(new BookItem("মিশর রহস্য", "উপন্যাস", "book.pdf"));
                bookItemList.add(new BookItem("হিমু", "উপন্যাস", "book.pdf"));
                bookItemList.add(new BookItem("মধ্যাহ্ন", "উপন্যাস", "book.pdf"));
                bookItemList.add(new BookItem("যা কিছু প্রেম", "ছোটগল্প সংকলন", "book.pdf"));
                bookItemList.add(new BookItem("স্মৃতির জন্য কিছু", "প্রবন্ধ", "book.pdf"));
                bookItemList.add(new BookItem("স্বপ্নের রাজ্যে", "উপন্যাস", "book.pdf"));
                break;

            case "জীবনানন্দ দাশ":
                bookItemList.add(new BookItem("বনলতা সেন", "কবিতা", "book.pdf"));
                bookItemList.add(new BookItem("স্বপ্নভঙ্গ", "কবিতা", "book.pdf"));
                bookItemList.add(new BookItem("রূপসী বাংলা", "কবিতা", "book.pdf"));
                bookItemList.add(new BookItem("অলীক", "কবিতা", "book.pdf"));
                bookItemList.add(new BookItem("নীরবী", "কবিতা", "book.pdf"));
                bookItemList.add(new BookItem("বসন্তের কাব্য", "কবিতা", "book.pdf"));
                bookItemList.add(new BookItem("যতই তুমি যতই", "কবিতা", "book.pdf"));
                bookItemList.add(new BookItem("মৃত্যু", "কবিতা", "book.pdf"));
                bookItemList.add(new BookItem("ছায়া", "কবিতা", "book.pdf"));
                break;

            case "শামসুর রাহমান":
                bookItemList.add(new BookItem("মৃত্যুক্ষণ", "কবিতা", "book.pdf"));
                bookItemList.add(new BookItem("খণ্ডকালীন", "কবিতা", "book.pdf"));
                bookItemList.add(new BookItem("আলো-মাছের কবিতা", "কবিতা", "book.pdf"));
                bookItemList.add(new BookItem("অগ্নিঝরা দিন", "কবিতা", "book.pdf"));
                bookItemList.add(new BookItem("ধোঁপকাচের সন্ন্যাসী", "ছোটগল্প", "book.pdf"));
                bookItemList.add(new BookItem("একজন মধ্যবয়সী মানুষ", "প্রবন্ধ", "book.pdf"));
                break;

            case "আখতারুজ্জামান":
                bookItemList.add(new BookItem("খোঁয়াবনামা", "উপন্যাস", "book.pdf"));
                bookItemList.add(new BookItem("চিলেকোঠার সেপাই", "উপন্যাস", "book.pdf"));
                bookItemList.add(new BookItem("দোজখের ওম", "ছোটগল্প সংকলন", "book.pdf"));
                bookItemList.add(new BookItem("আঁধারে গান", "ছোটগল্প সংকলন", "book.pdf"));
                bookItemList.add(new BookItem("খালি হাত", "ছোটগল্প সংকলন", "book.pdf"));
                break;



            default:
                bookItemList.add(new BookItem("No Items Found", "There are no items for this category", ""));
                break;
        }

        bookAdapter.notifyDataSetChanged(); // Notify adapter of data change
    }

    // Toolbar back button handler
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }



}//Public Class End Here
