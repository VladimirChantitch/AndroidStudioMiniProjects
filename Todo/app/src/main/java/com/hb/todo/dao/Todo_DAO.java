package com.hb.todo.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.SyncStateContract;

import com.hb.todo.db.TodoDBHelper;
import com.hb.todo.pojo.Todo_POJO;

import java.util.ArrayList;
import java.util.List;

public class Todo_DAO extends DAO{
    public Todo_DAO(Context context) {
        super(new TodoDBHelper(context));
    }

    public Todo_POJO find_Todo(Long id){
        Todo_POJO todo_pojo = null;

        open();

        Cursor cursor  = db.rawQuery("select * from " + TodoDBHelper.TODO_TABLE_NAME
                                        + " where " + TodoDBHelper.TODO_KEY + " = ?",
                                        new String[]{String.valueOf(id)});

        if (cursor != null && cursor.moveToFirst()){
            todo_pojo = new Todo_POJO(
                    cursor.getLong(TodoDBHelper.TODO_KEY_COLUMN_INDEX),
                    cursor.getString(TodoDBHelper.TODO_NAME_COLUMN_INDEX),
                    cursor.getString(TodoDBHelper.TODO_URGENCY_COLUMN_INDEX)
            );
        }

        close();

        return  todo_pojo;
    }

    public List<Todo_POJO> findAll_Todo(){
        List<Todo_POJO> todo_pojos = new ArrayList<>();

        open();

        Cursor cursor  = db.rawQuery("select * from " + TodoDBHelper.TODO_TABLE_NAME,
                                        null);

        if (cursor != null && cursor.moveToFirst()){
            while (!cursor.isAfterLast()){
                todo_pojos.add(new Todo_POJO(
                        cursor.getLong(TodoDBHelper.TODO_KEY_COLUMN_INDEX),
                        cursor.getString(TodoDBHelper.TODO_NAME_COLUMN_INDEX),
                        cursor.getString(TodoDBHelper.TODO_URGENCY_COLUMN_INDEX)
                ));

                cursor.moveToNext();
            }
        }

        close();

        return  todo_pojos;
    }

    public void add_Todo(Todo_POJO todo_pojo){
        open();

        ContentValues contentValues = new ContentValues();
        contentValues.put(TodoDBHelper.TODO_NAME, todo_pojo.getName());
        contentValues.put(TodoDBHelper.TODO_URGENCY, todo_pojo.getUrgency());

        Long id = db.insert(TodoDBHelper.TODO_TABLE_NAME, null, contentValues);
        todo_pojo.setId(id);

        close();
    }

    public void update_Todo(Todo_POJO todo_pojo){
        open();

        ContentValues contentValues = new ContentValues();
        contentValues.put(TodoDBHelper.TODO_NAME, todo_pojo.getName());
        contentValues.put(TodoDBHelper.TODO_URGENCY, todo_pojo.getUrgency());

        db.update(TodoDBHelper.TODO_TABLE_NAME, contentValues, TodoDBHelper.TODO_KEY + " = ? ",
                        new String[]{String.valueOf(todo_pojo.getId())});

        close();
    }

    public void remove_Todo(Todo_POJO todo_pojo){
        open();

        db.delete(TodoDBHelper.TODO_TABLE_NAME, TodoDBHelper.TODO_KEY + " = ? ",
                new String[]{String.valueOf(todo_pojo.getId())});

        close();
    }
}
