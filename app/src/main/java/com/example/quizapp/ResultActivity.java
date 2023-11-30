package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    TextView textView,textViewPass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        textView = findViewById(R.id.textView);
        textViewPass = findViewById(R.id.textViewPass);
        int score = getIntent().getIntExtra("Result",0);
        textView.setText("Score : " + score);
        if (score < 18) {
            textViewPass.setText("YOU ARE FAILED");
        }

        findViewById(R.id.btn_restart).setOnClickListener(
                restart->{
                    Intent intent  = new Intent(ResultActivity.this , MainActivity.class);
                    startActivity(intent);
                    finish();
                }
        );
    }
}