package com.momen.myecomarcefairbase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;

public class SpalshActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spalsh);

        SystemClock.sleep(3000);
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);


    }
}
