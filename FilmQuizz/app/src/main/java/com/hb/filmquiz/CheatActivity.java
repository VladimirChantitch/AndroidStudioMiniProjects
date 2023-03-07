package com.hb.filmquiz;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.hb.pojos.Question_POJO;

public class CheatActivity extends AppCompatActivity {

    TextView tvReponse = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getRef();
        receiveBundle();
    }

    private void getRef() {
        setContentView(R.layout.activity_cheat);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        tvReponse = findViewById(R.id.tvReponse);
    }

    private void receiveBundle() {
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        Question_POJO question = (Question_POJO) bundle.getSerializable(MainActivity.KEY_INDEX);
        tvReponse.setText(String.format("%s: %s", question.getQuestion(), question.getAnswer()));
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}