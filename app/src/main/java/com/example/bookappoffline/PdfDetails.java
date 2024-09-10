package com.example.bookappoffline;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.listener.OnLoadCompleteListener;
import com.github.barteksc.pdfviewer.listener.OnPageChangeListener;
import com.github.barteksc.pdfviewer.util.FitPolicy;

import java.io.IOException;
import java.io.InputStream;

public class PdfDetails extends AppCompatActivity {

    PDFView pdfView;
    ProgressBar progressBar;
    TextView book_title, currentPageNumber;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf_details);

        // For Toolbar back Button
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        // Initialize PDFView, ProgressBar, and TextView for displaying title
        pdfView = findViewById(R.id.pdfView);
        progressBar = findViewById(R.id.progressBar);
        book_title = findViewById(R.id.book_title);
        currentPageNumber = findViewById(R.id.currentPageNumber);



        // Get data passed from previous activity
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            String pdfFileName = bundle.getString("pdfFileName");
            String title = bundle.getString("title");

            // Set the title of the PDF file in the TextView
            book_title.setText(title);

            // Load the PDF from assets
            loadPDFFromAssets(pdfFileName);
        }




    } //onCreate End Here

    private void loadPDFFromAssets(String pdfFileName) {
        try {
            InputStream inputStream = getAssets().open(pdfFileName);
            pdfView.fromStream(inputStream)
                    .onLoad(new OnLoadCompleteListener() {
                        @Override
                        public void loadComplete(int nbPages) {
                            progressBar.setVisibility(View.GONE);
                        }
                    })
                    .spacing(0)
                    .pageFitPolicy(FitPolicy.WIDTH)
                    .enableSwipe(true)
                    .swipeHorizontal(true)
                    .enableDoubletap(true)
                    .defaultPage(0)
                    .scrollHandle(null)
                    .password(null)
                    .enableAnnotationRendering(true)
                    .enableAntialiasing(true)
                    .pageSnap(true)
                    .pageFling(true)
                    .onPageChange(new OnPageChangeListener() {
                        @Override
                        public void onPageChanged(int page, int pageCount) {
                            currentPageNumber.setText("পৃষ্ঠা নম্বর  "+page+"/"+pageCount);
                        }
                    })
                    .load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Toolbar back button handler
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}