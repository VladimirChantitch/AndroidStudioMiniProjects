package com.hb.bestyoutubeapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.security.keystore.StrongBoxUnavailableException;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.hb.bestyoutubeapp.dao.Video_DAO;
import com.hb.bestyoutubeapp.pojo.Video;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AddYoutubeVideoActivity extends AppCompatActivity {

    Button btn_validate = null;
    Button btn_cancel = null;
    Spinner s_category = null;
    EditText et_VideoTitle = null;
    EditText et_VideoDescription = null;
    EditText et_VideoUrl = null;

    Toast toast = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_youtube_video);

        getRef();
        initSpinner();
        bindButtons();
    }

    private void getRef() {
        setContentView(R.layout.activity_add_youtube_video);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        btn_cancel = findViewById(R.id.btn_cancel);
        btn_validate = findViewById(R.id.btn_validate);
        s_category = findViewById(R.id.s_category);
        et_VideoTitle = findViewById(R.id.et_title);
        et_VideoDescription = findViewById(R.id.et_descritpion);
        et_VideoUrl = findViewById(R.id.et_url);
    }

    private void initSpinner() {
        String[] categories = getResources().getStringArray(R.array.Categories);
        List<String> list = new ArrayList<>(Arrays.asList(categories));

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_dropdown_item_1line,
                list
        );

        s_category.setAdapter(adapter);
    }

    private void bindButtons() {
        btn_validate.setOnClickListener(
            new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String title = et_VideoTitle.getText().toString();
                    if(!handleErrorToast(title)){
                        return;
                    }
                    String url = et_VideoUrl.getText().toString();
                    if(!handleErrorToast(url)){
                        return;
                    }

                    AddVideo(title,
                            et_VideoDescription.getText().toString(),
                            url,
                            s_category.getSelectedItem().toString());

                    finish();
                }

            }
        );

        btn_cancel.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        finish();
                    }
                }
        );
    }

    public boolean handleErrorToast(String txt) {
        String toast_txt = " ";
        if (txt == null || txt.length() <= 1){
            toast_txt = "well some fields are mandatory";
            showToast(toast_txt);
            return false;
        }
        return true;
    }

    private void showToast(String txt){
        int duration = Toast.LENGTH_SHORT;
        if (toast != null){
            toast.cancel();
        }
        toast = Toast.makeText(getApplicationContext(), txt, duration);
        toast.show();
    }

    public void AddVideo(String title, String description, String url, String category){
        Video_DAO video_dao = new Video_DAO(getApplicationContext());

        Video video = new Video(
                -1,
                title,
                description,
                url,
                category
        );

        video_dao.add_Video(video);
    }
}