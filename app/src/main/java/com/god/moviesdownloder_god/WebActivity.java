package com.god.moviesdownloder_god;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class WebActivity extends AppCompatActivity {

    private RecyclerView webFirstRecy;
    private FirebaseFirestore Webfirestore;
    private  Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        getSupportActionBar().hide();

        webFirstRecy= findViewById(R.id.WebThemRecy);

        dialog= new Dialog(this);
        dialog.setContentView(R.layout.dialog);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();

        ArrayList<FilmSimple> WebSimple= new ArrayList<>();

        Webfirestore= FirebaseFirestore.getInstance();

        FilmAdaptor Fadaptor= new FilmAdaptor(this,WebSimple);
        Webfirestore.collection("WebFilm")
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {

                        WebSimple.clear();
                        for (DocumentSnapshot snapshot : value.getDocuments()){

                            FilmSimple WebThemb= snapshot.toObject(FilmSimple.class);
                            WebThemb.setFilmID(snapshot.getId());
                            WebSimple.add(WebThemb);

                        }
                        Fadaptor.notifyDataSetChanged();
                        dialog.dismiss();
                    }
                });


                 webFirstRecy.setLayoutManager(new GridLayoutManager(this,2));
                 webFirstRecy.setAdapter(Fadaptor);







    }
}