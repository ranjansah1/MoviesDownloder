package com.god.moviesdownloder_god;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.FirestoreRegistrar;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class FIrstActivity extends AppCompatActivity {

    private RecyclerView recyclerView,Film;
    private TextView hindiFilm,hindiDubbed,english,web;

    private FirebaseFirestore firestore ,frestore1;

    private Dialog dialog;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        getSupportActionBar().hide();
        recyclerView= findViewById(R.id.CardContainer);
        Film= findViewById(R.id.FilmContaner);
        hindiFilm= findViewById(R.id.HindiFilm);
        hindiDubbed= findViewById(R.id.HindiDubbedBtn);
        english= findViewById(R.id.EnglishBtn);
        web= findViewById(R.id.WebSBtn);

        dialog= new Dialog(this);
        dialog.setContentView(R.layout.dialog);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();

        AllFilBtn();

        firestore= FirebaseFirestore.getInstance();

        ArrayList<CardSample> cardSamples= new ArrayList<>();
        CardCategoryAdaptor categoryAdaptor= new CardCategoryAdaptor(this,cardSamples);


        firestore.collection("cardSamples")
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                        cardSamples.clear();
                        for (DocumentSnapshot snapshot : value.getDocuments()) {
                            CardSample model = snapshot.toObject(CardSample.class);
                            model.setCardID(snapshot.getId());
                            cardSamples.add(model);
                            dialog.dismiss();
                        }
                        categoryAdaptor.notifyDataSetChanged();
                    }
                });
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
       // recyclerView.setLayoutManager(new ViewPager(this,ViewPager.SCROLL_AXIS_HORIZONTAL,null));


        recyclerView.setAdapter(categoryAdaptor);


        frestore1= FirebaseFirestore.getInstance();
        ArrayList<FilmSimple> filmSimples= new ArrayList<>();
        FilmAdaptor filmAdaptor= new FilmAdaptor(this,filmSimples);

        frestore1.collection("filmSimples")
                        .addSnapshotListener(new EventListener<QuerySnapshot>() {
                            @Override
                            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {

                                filmSimples.clear();
                                for (DocumentSnapshot documentSnapshot:value.getDocuments()){

                                    FilmSimple f= documentSnapshot.toObject(FilmSimple.class);
                                    f.setFilmID(documentSnapshot.getId());
                                    filmSimples.add(f);
                                }
                                filmAdaptor.notifyDataSetChanged();


                            }
                        });

                     Film.setLayoutManager(new GridLayoutManager(this,2));

                      Film.setAdapter(filmAdaptor);



    }

    private  void AllFilBtn(){

        hindiFilm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent HindiInt= new Intent(FIrstActivity.this,MovieActivity.class);

                startActivity(HindiInt);
            }
        });

        hindiDubbed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent HindiDUbbedInt= new Intent(FIrstActivity.this,HindiDubbedActivity.class);

                startActivity(HindiDUbbedInt);
            }
        });

        english.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent EnglishInt= new Intent(FIrstActivity.this,EnglishActivity.class);

                startActivity(EnglishInt);


            }
        });

        web.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(FIrstActivity.this,WebActivity.class));

            }
        });
    }
}