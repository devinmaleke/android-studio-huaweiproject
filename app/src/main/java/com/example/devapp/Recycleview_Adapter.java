package com.example.devapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class Recycleview_Adapter extends RecyclerView.Adapter<Recycleview_Adapter.viewHolder>{

    private ArrayList<String> gambar = new ArrayList<>();
    private ArrayList<String> name = new ArrayList<>();
    private Context ct;

    public Recycleview_Adapter(ArrayList<String> gambar, ArrayList<String> name,Context ct) {
        this.gambar = gambar;
        this.name = name;
        this.ct = ct;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_adapter,parent,false);
        viewHolder holder = new viewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {

        Glide.with(ct).asBitmap().load(gambar.get(position)).into(holder.imageView);

        holder.nama.setText(name.get(position));


    }

    @Override
    public int getItemCount() {
        return name.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder{

       ImageView imageView;
       TextView nama;
       ConstraintLayout conLay;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            nama = itemView.findViewById(R.id.textViewNama);
            conLay = itemView.findViewById(R.id.conLay);

        }
    }
}
