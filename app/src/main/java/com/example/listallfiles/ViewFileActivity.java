package com.example.listallfiles;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.github.barteksc.pdfviewer.PDFView;
import com.squareup.picasso.Picasso;

import java.io.File;

public class ViewFileActivity extends AppCompatActivity {
    ImageView imageView;
    PDFView pdfView;
    String path;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_file);

        imageView = findViewById(R.id.imageView);
        pdfView = findViewById(R.id.pdfView);

        path = getIntent().getStringExtra("path");
        if (path != null && !path.isEmpty()) {
            if (FileAdapter.imageOrpdf == 0) { // 0 for Image
                imageView.setVisibility(View.VISIBLE);
                pdfView.setVisibility(View.GONE);
                Picasso.get().load(new File(path)).placeholder(R.drawable.ic_image).into(imageView);
            } else if (FileAdapter.imageOrpdf == 1) { // 1 for Pdf
                imageView.setVisibility(View.GONE);
                pdfView.setVisibility(View.VISIBLE);
                pdfView.fromFile(new File(path)).load();
            }
        }

    }
}