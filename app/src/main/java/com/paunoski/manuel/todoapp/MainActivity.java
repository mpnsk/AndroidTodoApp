package com.paunoski.manuel.todoapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        List<Todo> todos = new ArrayList<>();
        String s = "Todo #";
        for (int i = 0; i < 42; i++) {
            todos.add(new Todo(s + i));
        }
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recylerViewTodoList);
        recyclerView.setAdapter(new MyAdapter(todos));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
