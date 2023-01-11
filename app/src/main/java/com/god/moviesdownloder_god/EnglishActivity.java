package com.god.moviesdownloder_god;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class EnglishActivity extends AppCompatActivity {


    private RecyclerView Englishrecy;
    String hindi,hindiDubbed,english,web;
    ImageView englishBAckBtn;
    private FirebaseFirestore englishstore;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_english);
        getSupportActionBar().hide();
        Englishrecy= findViewById(R.id.EnglishRecy);
        englishBAckBtn= findViewById(R.id.EnglshBack);

        englishstore= FirebaseFirestore.getInstance();

        ArrayList<FilmSimple> EnglishMovies= new ArrayList<>();

        FilmAdaptor Moviedaptor= new FilmAdaptor(this,EnglishMovies);

        englishstore.collection("EnglishMovies")
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {

                        EnglishMovies.clear();
                        for (DocumentSnapshot snapshot : value.getDocuments()){

                            FilmSimple film= snapshot.toObject(FilmSimple.class);
                            film.setFilmID(snapshot.getId());
                            EnglishMovies.add(film);
                        }
                        Moviedaptor.notifyDataSetChanged();

                    }
                });
        Englishrecy.setLayoutManager(new GridLayoutManager(this,2));
        Englishrecy.setAdapter(Moviedaptor);

        englishBAckBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(EnglishActivity.this,FIrstActivity.class));
            }
        });
    }
}