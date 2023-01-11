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

public class CardCategoryAdaptor extends RecyclerView.Adapter<CardCategoryAdaptor.CardCAtegoryViewHolder> {

    Context context;
    ArrayList<CardSample> categoryModels;

    public CardCategoryAdaptor(Context context, ArrayList<CardSample> categoryModels) {
        this.context = context;
        this.categoryModels = categoryModels;
    }


    @NonNull
    @Override
    public CardCAtegoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.card_sample,null);
        return new CardCAtegoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CardCAtegoryViewHolder holder, int position) {

        final CardSample model = categoryModels.get(position);
        holder.CardCount.setText(model.getCardCoutTb());
        holder.CardHindiDubbed.setText(model.getCardHndiDubbed());
        Glide.with(context)
                .load(model.getCardImg())
                .into(holder.CardCateImg);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(context, CardDownloadeActivity.class);
                intent.putExtra("CardId",model.getCardID() );
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return categoryModels.size();
    }

    public class CardCAtegoryViewHolder extends RecyclerView.ViewHolder {
        ImageView CardCateImg;
        TextView CardCount,CardHindiDubbed;
        public CardCAtegoryViewHolder(@NonNull View itemView) {
            super(itemView);

            CardCateImg= itemView.findViewById(R.id.Sample_card_img);
            CardCount= itemView.findViewById(R.id.Card_countTb);
            CardHindiDubbed= itemView.findViewById(R.id.CardHindiDubbedTb);
        }
    }
}
