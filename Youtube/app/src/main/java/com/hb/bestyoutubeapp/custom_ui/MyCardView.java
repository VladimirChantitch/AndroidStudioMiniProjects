package com.hb.bestyoutubeapp.custom_ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;

import com.hb.bestyoutubeapp.R;

public class MyCardView extends CardView {
    public MyCardView(@NonNull Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public MyCardView(Context context) {
        super(context);
    }

    public MyCardView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private long VideoId;

    public void InitId(long videoId){
        this.VideoId = videoId;
    }

    public long getVideoId() {
        return VideoId;
    }

    public void setVideoId(long videoId) {
        VideoId = videoId;
    }
}
