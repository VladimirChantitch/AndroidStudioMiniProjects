package com.hb.todo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.hb.todo.adapter.TodoAdapter;
import com.hb.todo.dao.Todo_DAO;
import com.hb.todo.pojo.Todo_POJO;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    TextView tvAllTasks = null;
    private RecyclerView rvTodos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStart() {
        super.onStart();

        rvTodos = findViewById(R.id.rvTodo);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        rvTodos.setHasFixedSize(true);

        rvTodos.setLayoutManager(layoutManager);

        TodoAsyncTasks todoAsyncTasks = new TodoAsyncTasks();
        todoAsyncTasks.execute();
    }

    public List<Todo_POJO> getTodos(){
        Todo_DAO todo_dao = new Todo_DAO(getApplicationContext());
        List<Todo_POJO> list = todo_dao.findAll_Todo();
        for (Todo_POJO todo_pojo : list){
            Log.d("REQUEST", todo_pojo.getName());
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
        switch (item.getItemId()){
            case R.id.add:
                Intent intent = new Intent(getApplicationContext(), AddTodoActivity.class);
                Bundle bundle = new Bundle();
                intent.putExtras(bundle);
                startActivity(intent);
                return true;
            default:
                return  super.onOptionsItemSelected(item);
        }
    }

    public class TodoAsyncTasks extends AsyncTask<String, String, List<Todo_POJO>> {
        @Override
        protected List<Todo_POJO> doInBackground(String... strings) {
            Todo_DAO todo_dao = new Todo_DAO(getApplicationContext());
            List<Todo_POJO> todos = new ArrayList<>();

            try{
                todos = todo_dao.findAll_Todo();
            }catch(Exception e) {
                e.printStackTrace();
            }

            return todos;
        }

        @Override
        protected void onPostExecute(List<Todo_POJO> todo_pojos) {
            TodoAdapter todoAdapter = new TodoAdapter(todo_pojos);
            rvTodos.setAdapter(todoAdapter);
        }
    }
}