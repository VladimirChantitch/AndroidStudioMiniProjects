package com.hb.bestyoutubeapp.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hb.bestyoutubeapp.R;
import com.hb.bestyoutubeapp.pojo.Video;

import java.util.List;

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.VideoViewHolder> {

    private List<Video> videos;

    public class VideoViewHolder extends RecyclerView.ViewHolder{

        public TextView tv_title;
        public TextView tv_description;
        public TextView tv_category;

        public VideoViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_title = itemView.findViewById(R.id.tv_Title);
            tv_description = itemView.findViewById(R.id.tv_Description);
            tv_category = itemView.findViewById(R.id.tv_Category);
        }
    }

    public VideoAdapter(List<Video> viedos){
        this.videos = viedos;
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
}
