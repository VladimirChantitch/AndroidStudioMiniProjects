package com.hb.todo;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.PendingIntent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.hb.todo.dao.Todo_DAO;
import com.hb.todo.pojo.Todo_POJO;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AddTodoActivity extends AppCompatActivity {

    Todo_DAO todo_dao = null;

    Button btn_validate = null;
    Button btn_cancel = null;
    Spinner lv_Urgency = null;
    EditText et_TaskName = null;

    Toast toast = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getRef();
        initSpinner();
        bindButtons();
    }

    private void getRef() {
        setContentView(R.layout.activity_add_todo);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        btn_cancel = findViewById(R.id.btn_cancel);
        btn_validate = findViewById(R.id.btn_validate);
        lv_Urgency = findViewById(R.id.lv_Urgency);
        et_TaskName = findViewById(R.id.et_TaskName);
    }

    private void initSpinner() {
        String[] urgencies = getResources().getStringArray(R.array.urgencies);
        List<String> list = new ArrayList<>(Arrays.asList(urgencies));

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_dropdown_item_1line,
                list
        );

        lv_Urgency.setAdapter(adapter);
    }

    private void bindButtons() {
        btn_validate.setOnClickListener(
            new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String txt = et_TaskName.getText().toString();
                    if(txt.length() <= 3){
                        tooShortToast();
                    }
                    AddTodo(txt, lv_Urgency.getSelectedItem().toString());
                    finish();
                }
                public void tooShortToast() {
                    String txt = "Pleas write something longer than 3 characters";
                    int duration = Toast.LENGTH_SHORT;
                    if (toast != null){
                        toast.cancel();
                    }
                    toast = Toast.makeText(getApplicationContext(), txt, duration);
                    toast.show();
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

    public void AddTodo(String name, String urgence){
        todo_dao = new Todo_DAO(getApplicationContext());
        Todo_POJO todo_pojo = new Todo_POJO(
                -1,
                name,
                urgence
        );

        todo_dao.add_Todo(todo_pojo);
    }
}