package com.god.moviesdownloder_god;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class FilmAdaptor extends RecyclerView.Adapter<FilmAdaptor.FilmViewHolder> {

    Context context;
    ArrayList<FilmSimple> filmSimples;
    public FilmAdaptor(Context context, ArrayList<FilmSimple> filmSimples){

        this.context=context;
        this.filmSimples= filmSimples;

    }
    @NonNull
    @Override
    public FilmViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(context).inflate(R.layout.film_sample,null);
        return new FilmViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FilmViewHolder holder, int position) {

        final  FilmSimple Modal= filmSimples.get(position);

        holder.FilmDate.setText(Modal.getFIlmDate());
        holder.FilmDubbed.setText(Modal.getFilmDubbed());
        holder.FilmName.setText(Modal.getFilmName());
        Glide.with(context)
                .load(Modal.getFIlmimg())
                .into(holder.FilmImg);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(context, DownlodeActivity.class);
                intent.putExtra("catId", Modal.getFilmID());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return filmSimples.size();
    }

    public  class FilmViewHolder extends RecyclerView.ViewHolder {

        ImageView FilmImg;
        TextView FilmName,FilmDubbed,FilmDate;

        public FilmViewHolder(@NonNull View itemView) {
            super(itemView);
            FilmImg= itemView.findViewById(R.id.filmSimpleImg);
            FilmName= itemView.findViewById(R.id.FilmNameTb);
            FilmDubbed= itemView.findViewById(R.id.filmHindiDTB);
            FilmDate= itemView.findViewById(R.id.filmDate);
        }
    }
}
