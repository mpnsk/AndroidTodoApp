package com.paunoski.manuel.todoapp;

import android.arch.lifecycle.ViewModelProviders;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.paunoski.manuel.todoapp.db.Todo;

import java.util.ArrayList;
import java.util.List;


public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private final TodoViewModel todoViewModel;
    private List<Todo> list = new ArrayList<>();

    MyAdapter(List<Todo> list, FragmentActivity activity) {
        this.list = list;
        todoViewModel = ViewModelProviders.of(activity).get(TodoViewModel.class);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_todo, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Todo todo = list.get(position);
        holder.textView.setText(todo.toString());
        holder.imageView.setOnClickListener(view -> todoViewModel.removeTodo(list.get(position)));
    }

    void setList(List<Todo> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView textView;
        CheckBox checkBox;
        ImageView imageView;

        ViewHolder(View itemView) {
            super(itemView);
            if (textView == null) {
                textView = itemView.findViewById(R.id.textViewItem);
            }
            if (checkBox == null) {
                checkBox = itemView.findViewById(R.id.checkBoxDone);
            }
            if (imageView == null) {
                imageView = itemView.findViewById(R.id.img_btn_delete_item);
            }
        }
    }
}
