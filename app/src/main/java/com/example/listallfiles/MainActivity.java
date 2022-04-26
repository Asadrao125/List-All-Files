package com.example.listallfiles;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    Button btnGetAllImages, btnAllPdfs;
    ArrayList<File> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnGetAllImages = findViewById(R.id.btnGetAllImages);
        btnAllPdfs = findViewById(R.id.btnAllPdfs);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        btnGetAllImages.setOnClickListener(view -> {
            list.clear();
            File dir = new File(Environment.getExternalStorageDirectory().getAbsolutePath());
            getfile(dir, ".jpg");
            recyclerView.setAdapter(new FileAdapter(getApplicationContext(), list, R.drawable.ic_image));
        });

        btnAllPdfs.setOnClickListener(view -> {
            list.clear();
            File dir = new File(Environment.getExternalStorageDirectory().getAbsolutePath());
            getfile(dir, ".pdf");
            recyclerView.setAdapter(new FileAdapter(getApplicationContext(), list, R.drawable.ic_pdf));
        });
    }

    public ArrayList<File> getfile(File dir, String fileExtension) {
        File[] listFile = dir.listFiles();
        if (listFile != null && listFile.length > 0) {
            for (File file : listFile) {
                if (file.isDirectory()) {
                    getfile(file, fileExtension);
                } else {
                    if (file.getName().endsWith(fileExtension)) {
                        list.add(file);
                    }
                }
            }
        }
        return list;
    }
    /* if (file.getName().endsWith(".pdf")
                            || file.getName().endsWith(".xls")
                            || file.getName().endsWith(".jpg")
                            || file.getName().endsWith(".jpeg")
                            || file.getName().endsWith(".png")
                            || file.getName().endsWith(".doc")) {
                        list.add(file);
                    } */
}