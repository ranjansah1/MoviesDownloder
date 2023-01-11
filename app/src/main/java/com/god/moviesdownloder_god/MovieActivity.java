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
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class MovieActivity extends AppCompatActivity {

    private RecyclerView movieRecy;
    String hindi,hindiDubbed,english,web;
    private ImageView movieBackBtn;
     private FirebaseFirestore store,f;
     TextView t1;
     private  Dialog dialog;

    ArrayList<FilmSimple> MoviesSimples= new ArrayList<>();
     ArrayList<FilmSimple> HindiDubbed= new ArrayList<>();
    FilmAdaptor Moviedaptor= new FilmAdaptor(this,MoviesSimples);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);
        getSupportActionBar().hide();
        movieRecy= findViewById(R.id.MovieRecy);
        t1= findViewById(R.id.MovieT1);
        movieBackBtn= findViewById(R.id.MovieBack);

        store= FirebaseFirestore.getInstance();
        dialog= new Dialog(this);
        dialog.setContentView(R.layout.dialog);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();


          store = FirebaseFirestore.getInstance();


          AllMovieCategory();

        movieRecy.setLayoutManager(new GridLayoutManager(this,2));
        movieRecy.setAdapter(Moviedaptor);

        movieBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(MovieActivity.this,FIrstActivity.class));
            }
        });


    }

    private  void AllMovieCategory(){


            store.collection("MoviesSimples")
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



    }



}