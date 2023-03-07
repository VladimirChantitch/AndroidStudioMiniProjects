package com.hb.bestyoutubeapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.hb.bestyoutubeapp.adapter.VideoAdapter;
import com.hb.bestyoutubeapp.dao.Video_DAO;
import com.hb.bestyoutubeapp.pojo.Video;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rv_Video;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Video_DAO video_dao = new Video_DAO(getApplicationContext());
        List<Video> videos = video_dao.findAll_Video();
        if (videos != null) {
            videos.forEach(v -> Log.d("TOTOTO", v.getTitle()));
        }
    }

    @Override
    protected void onStart() {
        super.onStart();

        rv_Video = findViewById(R.id.rv_Video);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        rv_Video.setHasFixedSize(true);

        rv_Video.setLayoutManager(layoutManager);

        VideoAsyncTasks todoAsyncTasks = new VideoAsyncTasks();
        todoAsyncTasks.execute();
    }

    public List<Video> getVideos(){
        Video_DAO todo_dao = new Video_DAO(getApplicationContext());
        List<Video> list = todo_dao.findAll_Video();
        for (Video video : list){
            Log.d("REQUEST", video.getTitle());
        }

        return list;
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d("WARNING", "onSaveInstanceState() called");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent intent = null;
        Bundle bundle = null;
        switch (item.getItemId()){
            case R.id.AddVideo:
                intent = new Intent(getApplicationContext(), AddYoutubeVideoActivity.class);
                bundle = new Bundle();
                intent.putExtras(bundle);
                startActivity(intent);
                return true;
            case R.id.ShowDetails:
                intent = new Intent(getApplicationContext(), VideoDetailsActivity.class);
                bundle = new Bundle();
                intent.putExtras(bundle);
                startActivity(intent);
                return true;
            default:
                return  super.onOptionsItemSelected(item);
        }
    }

    public class VideoAsyncTasks extends AsyncTask<String, String, List<Video>> {
        @Override
        protected List<Video> doInBackground(String... strings) {
            Video_DAO video_dao = new Video_DAO(getApplicationContext());
            List<Video> videos = new ArrayList<>();

            try{
                videos = video_dao.findAll_Video();
            }catch(Exception e) {
                e.printStackTrace();
            }

            return videos;
        }

        @Override
        protected void onPostExecute(List<Video> videos) {
            VideoAdapter videoAdapter = new VideoAdapter(videos);
            rv_Video.setAdapter(videoAdapter);
        }
    }
}