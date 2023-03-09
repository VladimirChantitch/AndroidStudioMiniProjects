package com.hb.bestyoutubeapp.adapter;

import static java.lang.Math.max;
import static java.lang.Math.min;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.hb.bestyoutubeapp.R;
import com.hb.bestyoutubeapp.pojo.Video;

import java.util.ArrayList;
import java.util.List;

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.VideoViewHolder> {

    private List<Video> videos;
    private List<CardView> cardViews = new ArrayList<>();
    Context context = null;
    int numberofVideos = 0;
    public class VideoViewHolder extends RecyclerView.ViewHolder{
        public TextView tv_title;
        public TextView tv_description;
        public TextView tv_category;

        public VideoViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_title = itemView.findViewById(R.id.tv_Title);
            tv_description = itemView.findViewById(R.id.tv_Description);
            tv_category = itemView.findViewById(R.id.tv_Category);
            if (itemView instanceof CardView) {
                cardViews.add(((CardView) itemView));
            }
            if (numberofVideos >= cardViews.size()){
                SetUpEventsOnCardViews();
            }
        }
    }

    public VideoAdapter(List<Video> videos, Context context){
        numberofVideos = videos.size();
        this.context = context;
        this.videos = videos;
    }

    @NonNull
    @Override
    public VideoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.youtube_video_item,
                parent, false);
        return new VideoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VideoViewHolder holder, int position) {
        Video video = videos.get(position);
        holder.tv_title.setText(video.getTitle());
        holder.tv_description.setText(video.getDescription());
        holder.tv_category.setText(video.getCategory());
    }

    @Override
    public int getItemCount() {
        return videos.size();
    }

    public List<CardView> getCards(){
        return cardViews;
    }

    @SuppressLint("ClickableViewAccessibility")
    private void SetUpEventsOnCardViews() {
        for (int i = 0; i < cardViews.size(); i++) {
            CardView cardView = cardViews.get(i);
            cardView.setOnTouchListener((view, event) -> {
                        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
                        int cardWidth = cardView.getWidth();
                        float cardStart = ((float)metrics.widthPixels / 2) - cardWidth / 2;
                        switch (event.getAction()) {
                            case MotionEvent.ACTION_MOVE:
                                float newX = event.getRawX();
                                // Swipe left
                                if (newX - cardWidth < cardStart){
                                    cardView.animate()
                                            .x(min(cardStart, newX - (cardWidth/2)))
                                            .setDuration(0)
                                            .start();
                                }
                                break;
                            case MotionEvent.ACTION_UP:
                                // TODO: Handle ACTION_UP
                                break;
                        }
                        return true;
                    }
            );
        }
    }
}
