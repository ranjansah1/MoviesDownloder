package com.god.moviesdownloder_god;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        this.getWindow().
                setFlags(WindowManager.LayoutParams.
                        FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Thread thread= new Thread(){

            @Override
            public void run() {

                try {
                    sleep(4000);

                }catch (Exception e){
                    e.getLocalizedMessage();

                }
                finally {
                    startActivity(new Intent(MainActivity.this,FIrstActivity.class));
                    finish();
                }
            }
        };thread.start();

    }
}