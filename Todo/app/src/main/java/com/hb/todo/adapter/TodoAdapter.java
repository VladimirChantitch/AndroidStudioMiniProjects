package com.hb.todo.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hb.todo.R;
import com.hb.todo.pojo.Todo_POJO;

import java.util.List;

public class TodoAdapter extends RecyclerView.Adapter<TodoAdapter.TodoViewHolder> {

    private List<Todo_POJO> todos;

    public class TodoViewHolder extends RecyclerView.ViewHolder{

        public TextView tvName;
        public TextView tvUrgency;

        public TodoViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_Name);
            tvUrgency = itemView.findViewById(R.id.tv_Urgency);
        }
    }

    public TodoAdapter(List<Todo_POJO> todos){
        this.todos = todos;
    }

    @NonNull
    @Override
    public TodoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.todo_item,
                parent, false);
        return new TodoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TodoViewHolder holder, int position) {
        Todo_POJO todo_pojo = todos.get(position);
        holder.tvName.setText(todo_pojo.getName());
        holder.tvUrgency.setText(todo_pojo.getUrgency());
    }

    @Override
    public int getItemCount() {
        return todos.size();
    }
}
