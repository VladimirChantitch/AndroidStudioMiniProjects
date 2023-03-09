package com.hb.bestyoutubeapp.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.hb.bestyoutubeapp.db.DB_Helper;
import com.hb.bestyoutubeapp.pojo.Video;

import java.util.ArrayList;
import java.util.List;

public class Video_DAO extends DAO{
    public Video_DAO(Context context) {super(new DB_Helper(context));}

    public Video find_Video(Long id){
        Video video = null;

        open();

        Cursor cursor  = db.rawQuery("select * from " + DB_Helper.VIDEO_TABLE_NAME
                        + " where " + DB_Helper.VIDEO_KEY + " = ?",
                new String[]{String.valueOf(id)});

        if (cursor != null && cursor.moveToFirst()){
            video = new Video(
                    cursor.getLong(DB_Helper.VIDEO_KEY_COLUMN_INDEX),
                    cursor.getString(DB_Helper.VIDEO_TITLE_COLUMN_INDEX),
                    cursor.getString(DB_Helper.VIDEO_DESCRIPTION_COLUMN_INDEX),
                    cursor.getString(DB_Helper.VIDEO_URL_COLUMN_INDEX),
                    cursor.getString(DB_Helper.VIDEO_CATEGORY_COLUMN_INDEX)
            );
        }

        close();

        return  video;
    }

    public List<Video> findAll_Video(){
        List<Video> videos = new ArrayList<>();

        open();

        Cursor cursor  = db.rawQuery("select * from " + DB_Helper.VIDEO_TABLE_NAME,
                null);

        if (cursor != null && cursor.moveToFirst()){
            while (!cursor.isAfterLast()){
                videos.add(
                    new Video(
                        cursor.getLong(DB_Helper.VIDEO_KEY_COLUMN_INDEX),
                        cursor.getString(DB_Helper.VIDEO_TITLE_COLUMN_INDEX),
                        cursor.getString(DB_Helper.VIDEO_DESCRIPTION_COLUMN_INDEX),
                        cursor.getString(DB_Helper.VIDEO_URL_COLUMN_INDEX),
                        cursor.getString(DB_Helper.VIDEO_CATEGORY_COLUMN_INDEX)
                    )
                );

                cursor.moveToNext();
            }
        }

        close();

        return  videos;
    }

    public void add_Video(Video video){
        open();

        ContentValues contentValues = new ContentValues();
        contentValues.put(DB_Helper.VIDEO_TITLE, video.getTitle());
        contentValues.put(DB_Helper.VIDEO_DESCRIPTION, video.getDescription());
        contentValues.put(DB_Helper.VIDEO_URL, video.getUrl());
        contentValues.put(DB_Helper.VIDEO_CATEGORY, video.getCategory());

        Long id = db.insert(DB_Helper.VIDEO_TABLE_NAME, null, contentValues);
        video.setId(id);

        close();
    }

    public void update_Video(Video video){
        open();

        ContentValues contentValues = new ContentValues();
        contentValues.put(DB_Helper.VIDEO_TITLE, video.getTitle());
        contentValues.put(DB_Helper.VIDEO_DESCRIPTION, video.getDescription());
        contentValues.put(DB_Helper.VIDEO_URL, video.getUrl());
        contentValues.put(DB_Helper.VIDEO_CATEGORY, video.getDescription().toString());

        db.update(DB_Helper.VIDEO_TABLE_NAME, contentValues, DB_Helper.VIDEO_KEY + " = ? ",
                new String[]{String.valueOf(video.getId())});

        close();
    }

    public void remove_Video(Video video) {
        open();

        db.delete(DB_Helper.VIDEO_TABLE_NAME, DB_Helper.VIDEO_KEY + " = ? ",
                new String[]{String.valueOf(video.getId())});

        close();
    }

    public void remove_VideoById(long id) {
        open();

        db.delete(DB_Helper.VIDEO_TABLE_NAME, DB_Helper.VIDEO_KEY + " = ? ",
                new String[]{String.valueOf(id)});

        close();
    }
}
