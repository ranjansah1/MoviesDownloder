package com.god.moviesdownloder_god;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class HindiDubbedActivity extends AppCompatActivity {

    private RecyclerView HindiDUbbed;
    String hindi,hindiDubbed,english,web;
    private ImageView HindiDubbedBackBtn;
    private FirebaseFirestore store,f;
    private  Dialog dialog;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hindi_dubbed);
        getSupportActionBar().hide();

        HindiDUbbed= findViewById(R.id.HindiDubbedRecy);
        HindiDubbedBackBtn = findViewById(R.id.HindiDubbedBack);

        dialog= new Dialog(this);
        dialog.setContentView(R.layout.dialog);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();

        store= FirebaseFirestore.getInstance();


        store = FirebaseFirestore.getInstance();




        ArrayList<FilmSimple> MoviesSimples= new ArrayList<>();

        FilmAdaptor Moviedaptor= new FilmAdaptor(this,MoviesSimples);
        store.collection("HindiDubbed")
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {

                        MoviesSimples.clear();
                        for (DocumentSnapshot snapshot : value.getDocuments()){

                            FilmSimple film= snapshot.toObject(FilmSimple.class);
                            film.setFilmID(snapshot.getId());
                            MoviesSimples.add(film);
                            dialog.dismiss();
                        }
                        Moviedaptor.notifyDataSetChanged();

                    }
                });
        HindiDUbbed.setLayoutManager(new GridLayoutManager(this,2));
        HindiDUbbed.setAdapter(Moviedaptor);

        HindiDubbedBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(HindiDubbedActivity.this,FIrstActivity.class));
            }
        });

    }



}