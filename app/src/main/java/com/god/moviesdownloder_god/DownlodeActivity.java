package com.god.moviesdownloder_god;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class DownlodeActivity extends AppCompatActivity {

    FirebaseFirestore firestore;
    TextView Downlodebtn;
      String catId,Cardd;
    private CountDownTimer timer;
    String MovieUrl;
    ArrayList<Hindi> hindis= new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_downlode);
        getSupportActionBar().hide();
       // Uri= findViewById(R.id.uri);

        Downlodebtn= findViewById(R.id.DownloadeBtn);
        GenerateLink();

        Downlodebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (MovieUrl.equals("")){

                    Toast.makeText(DownlodeActivity.this, "Server Not Available !", Toast.LENGTH_SHORT).show();
                }
                else {
                    Intent IntDow = new Intent(Intent.ACTION_VIEW);
                    IntDow.setPackage("com.android.chrome");
                    IntDow.setData(Uri.parse(MovieUrl));

                    startActivity(IntDow);
                   // Toast.makeText(DownlodeActivity.this, "Downloading..", Toast.LENGTH_SHORT).show();
                }
            }
        });


        // Hindi hindi= new Hindi();
         catId = getIntent().getStringExtra("catId");
         Cardd = getIntent().getStringExtra("CardId");
         firestore= FirebaseFirestore.getInstance();



        firestore.collection("MoviesSimples")
                .document(catId)
                .collection("Hindi")
                .get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {


                        for (DocumentSnapshot snapshot: queryDocumentSnapshots){
                            Hindi h= snapshot.toObject(Hindi.class);
                            hindis.add(h);

                            //MovieUrl.setText(h.getFullHD());

                            MovieUrl = h.getFullHD();
                            //Toast.makeText(DownlodeActivity.this, "DOne", Toast.LENGTH_SHORT).show();
                        }


                    }
                });
        AllDown();





    }

    private void GenerateLink() {

        timer = new CountDownTimer(10000, 1000) {
            @Override
            public void onTick(long l) {
                Downlodebtn.setText(String.valueOf("Wait.." + "  " + l / 1000));

                Downlodebtn.setEnabled(false);
                Downlodebtn.setBackground(getDrawable(R.drawable.cat_bg));
                Downlodebtn.setTextColor(getResources().getColor(R.color.SHADOW));
               // Toast.makeText(DownlodeActivity.this, "Please Wait...", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFinish() {

                Downlodebtn.setText("Downlode Now");
                Downlodebtn.setBackground(getDrawable(R.drawable.red_bg));
                Downlodebtn.setEnabled(true);

            }
        }.start();

    }



    private  void AllDown(){

        firestore.collection("HindiDubbed")
                .document(catId)
                .collection("Hindi")
                .get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        for (DocumentSnapshot snapshot: queryDocumentSnapshots){
                            Hindi h= snapshot.toObject(Hindi.class);
                            hindis.add(h);
                            //MovieUrl.setText(h.getFullHD());

                            MovieUrl = h.getFullHD();
                            //Toast.makeText(DownlodeActivity.this, "DOne", Toast.LENGTH_SHORT).show();
                        }


                    }
                });

        firestore.collection("filmSimples")
                .document(catId)
                .collection("Hindi")
                .get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

                        for (DocumentSnapshot snapshot: queryDocumentSnapshots){
                            Hindi h= snapshot.toObject(Hindi.class);
                            hindis.add(h);
                            // filmSimples
                            //MovieUrl.setText(h.getFullHD());

                            MovieUrl = h.getFullHD();
                            //Toast.makeText(DownlodeActivity.this, "DOne", Toast.LENGTH_SHORT).show();
                        }


                    }
                });


        firestore.collection("EnglishMovies")
                .document(catId)
                .collection("Hindi")
                .get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {


                        for (DocumentSnapshot snapshot: queryDocumentSnapshots){
                            Hindi h= snapshot.toObject(Hindi.class);
                            hindis.add(h);
                            // filmSimples
                            //MovieUrl.setText(h.getFullHD());

                            MovieUrl = h.getFullHD();
                            //Toast.makeText(DownlodeActivity.this, "DOne", Toast.LENGTH_SHORT).show();
                        }


                    }
                });


    }


}