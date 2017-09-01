package com.paunoski.manuel.todoapp;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.paunoski.manuel.todoapp.db.Todo;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatLifecycleActivity {

    private EditText editText;
    private MyAdapter adapter;
    private List<Todo> list;
    private TodoViewModel todoViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        list = new ArrayList<>();
        String s = "Todo #";
        for (int i = 0; i < 3; i++) {
            Todo todo = new Todo();
            todo.text = s + i;
            list.add(todo);
        }
        RecyclerView recyclerView = findViewById(R.id.recylerViewTodoList);
        adapter = new MyAdapter(list);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        todoViewModel = ViewModelProviders.of(this).get(TodoViewModel.class);
        todoViewModel.getObservableTodos().observe(this, todos -> adapter.setList(todos));

        Button button = findViewById(R.id.buttonSubmit);
        editText = findViewById(R.id.editTextInput);
        editText.setOnEditorActionListener((TextView v, int actionId, KeyEvent event) -> {
            if (actionId == EditorInfo.IME_ACTION_DONE) button.performClick();
            return false;
        });
    }

    public void submit(View view) {
        Todo todo = new Todo();
        todo.text = editText.getText().toString();
        todoViewModel.insertTodo(todo);
        editText.setText("");
    }
}
