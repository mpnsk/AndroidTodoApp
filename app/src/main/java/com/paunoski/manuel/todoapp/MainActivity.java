package com.paunoski.manuel.todoapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.paunoski.manuel.todoapp.db.Todo;
import com.paunoski.manuel.todoapp.db.TodoDao;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText editText;
    private MyAdapter adapter;
    private List<Todo> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        list = new ArrayList<>();
        String s = "Todo #";
        for (int i = 0; i < 3; i++) {
            Todo todo = TodoDao.createTodo(this);
            todo.text = s + i;
            list.add(todo);
        }
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recylerViewTodoList);
        adapter = new MyAdapter(list);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Button button = (Button) findViewById(R.id.buttonSubmit);
        editText = (EditText) findViewById(R.id.editTextInput);
        editText.setOnEditorActionListener((TextView v, int actionId, KeyEvent event) -> {
            if (actionId == EditorInfo.IME_ACTION_DONE) button.performClick();
            return false;
        });
    }

    public void submit(View view) {
        Todo todo = TodoDao.createTodo(this);
        todo.text = editText.getText().toString();
        list.add(todo);
        adapter.notifyItemInserted(list.size() - 1);
        editText.setText("");
    }
}
