package com.example.listallfiles;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.MimeTypeMap;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.File;
import java.util.ArrayList;

public class FileAdapter extends RecyclerView.Adapter<FileAdapter.MyViewHolder> {
    Context context;
    ArrayList<File> list;
    int image;
    public static int imageOrpdf = -1;

    public FileAdapter(Context c, ArrayList<File> l, int icon) {
        context = c;
        list = l;
        this.image = icon;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_pdf, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, int position) {
        holder.fileName.setText(list.get(position).getName());
        holder.imgFile.setImageResource(image);

        holder.itemView.setOnClickListener(view -> {
            if (image == R.drawable.ic_image) {
                imageOrpdf = 0;
            } else if (image == R.drawable.ic_pdf) {
                imageOrpdf = 1;
            }
            Intent intent = new Intent(context, ViewFileActivity.class);
            intent.putExtra("path", list.get(position).getAbsolutePath());
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView fileName;
        ImageView imgFile;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            fileName = itemView.findViewById(R.id.fileName);
            imgFile = itemView.findViewById(R.id.imgFile);
        }
    }
}