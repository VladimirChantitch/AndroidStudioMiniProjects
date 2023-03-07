package com.hb.filmquiz;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.os.Debug;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.hb.pojos.Question_POJO;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    Toast toast;
    private Button true_Btn;
    private Button false_Btn;
    private TextView tvQuestion = null;
    private TextView tvScore = null;
    List<Question_POJO> questions = new ArrayList<>();
    private int currentQuestion_idx = 0;
    private int currentScore = 0;

    public static final String KEY_INDEX = "index";
    public static final String KEY_SCORE = "score";

    boolean isFinished = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bindButtons();
        createQuestions();
        raloadData(savedInstanceState);
        updateTextFields();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d("[Warning]", "onSaveInstanceState() called");

        outState.putInt(KEY_INDEX,currentQuestion_idx);
        outState.putInt(KEY_SCORE,currentQuestion_idx);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.cheat:
                Intent intent = new Intent(getApplicationContext(), CheatActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable(KEY_INDEX, questions.get(currentQuestion_idx));
                intent.putExtras(bundle);
                startActivity(intent);
                return true;
            default:
                return  super.onOptionsItemSelected(item);
        }
    }

    private void bindButtons(){
        true_Btn = findViewById(R.id.true_btn);
        false_Btn = findViewById(R.id.false_btn);

        true_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isFinished) return;
                Context context = getApplicationContext();
                CharSequence txt = "You Chose True, AND YOU WRONG";

                if (questions.get(currentQuestion_idx).getAnswer() == true){
                    currentScore += 1;
                    txt = "You Chose True, AND YOU RIGHT";
                }

                int duration = Toast.LENGTH_SHORT;
                if (toast != null){
                    toast.cancel();
                }
                toast = Toast.makeText(context, txt, duration);
                toast.show();

                if (questions.stream().count() > currentQuestion_idx){
                    currentQuestion_idx +=1;
                }
                updateTextFields();
            }
        });

        false_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isFinished) return;
                Context context = getApplicationContext();
                CharSequence txt = "You Chose False, AND YOU WRONG";

                if (questions.get(currentQuestion_idx).getAnswer() == false){
                    currentScore += 1;
                    txt = "You Chose False, AND YOU RIGHT";
                }

                int duration = Toast.LENGTH_SHORT;
                if (toast != null){
                    toast.cancel();
                }
                toast = Toast.makeText(context, txt, duration);
                toast.show();

                if (questions.stream().count() > currentQuestion_idx){
                    currentQuestion_idx +=1;
                }
                updateTextFields();
            }
        });
    }

    private void createQuestions() {
        questions.add(new Question_POJO(getString(R.string.question_ai), true));
        questions.add(new Question_POJO(getString(R.string.question_taxi_driver), true));
        questions.add(new Question_POJO(getString(R.string.question_2001), false));
        questions.add(new Question_POJO(getString(R.string.question_reservoir_dogs), true));
        questions.add(new Question_POJO(getString(R.string.question_citizen_kane), false));
    }

    private void raloadData(Bundle savedInstanceState) {
        if (savedInstanceState != null){
            currentQuestion_idx = savedInstanceState.getInt(KEY_INDEX);
            currentScore = savedInstanceState.getInt(KEY_SCORE);
        }
    }
    private void updateTextFields() {
        if (isFinished) return;
        if (tvQuestion == null || tvScore == null){
            tvQuestion = findViewById(R.id.Question);
            tvScore = findViewById(R.id.Score);
        }

        if (currentQuestion_idx < questions.stream().count()){
            if (questions.get(currentQuestion_idx) != null){
                tvQuestion.setText(questions.get(currentQuestion_idx).getQuestion());
            }
        }
        else
        {
            Context context = getApplicationContext();
            CharSequence txt = "You finished the quizz Sorry";
            int duration = Toast.LENGTH_SHORT;
            if (toast != null){
                toast.cancel();
            }
            toast = Toast.makeText(context, txt, duration);
            toast.show();
            isFinished = true;
        }

        tvScore.setText(String.valueOf(currentScore));
    }
}