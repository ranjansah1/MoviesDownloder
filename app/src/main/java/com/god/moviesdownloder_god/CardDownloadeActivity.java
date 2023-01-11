package com.god.moviesdownloder_god;

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
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class CardDownloadeActivity extends AppCompatActivity {

    FirebaseFirestore firestore;
    TextView Downlodebtn1;
    String catId,Cardd;
    private CountDownTimer timer;
    String MovieUrl;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_downloade);
        getSupportActionBar().hide();


        Cardd = getIntent().getStringExtra("CardId");
        firestore= FirebaseFirestore.getInstance();
        ArrayList<Hindi> hindis= new ArrayList<>();
        Downlodebtn1= findViewById(R.id.CardDownloadeBtn);

        GenerateLink();

        firestore.collection("cardSamples")
                .document(Cardd)
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
        Downlodebtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (MovieUrl.equals("")){

                    Toast.makeText(CardDownloadeActivity.this, "Server Field To load !", Toast.LENGTH_SHORT).show();
                }
                else {
                    Intent IntDow = new Intent(Intent.ACTION_VIEW);
                    IntDow.setPackage("com.android.chrome");
                    IntDow.setData(Uri.parse(MovieUrl));
                    startActivity(IntDow);
                    Toast.makeText(CardDownloadeActivity.this, "Downloading..", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    private void GenerateLink() {

        timer = new CountDownTimer(10000, 1000) {
            @Override
            public void onTick(long l) {
                Downlodebtn1.setText(String.valueOf("Wait.." + "  " + l / 1000));

                Downlodebtn1.setEnabled(false);
                Downlodebtn1.setBackground(getDrawable(R.drawable.cat_bg));
                Downlodebtn1.setTextColor(getResources().getColor(R.color.SHADOW));
               // Toast.makeText(CardDownloadeActivity.this, "Please Wait...", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFinish() {

                Downlodebtn1.setText("Downlode Now");
                Downlodebtn1.setBackground(getDrawable(R.drawable.red_bg));
                Downlodebtn1.setEnabled(true);

            }
        }.start();

    }
}