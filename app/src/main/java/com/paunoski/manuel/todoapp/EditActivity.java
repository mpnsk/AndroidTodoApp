package com.paunoski.manuel.todoapp;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.paunoski.manuel.todoapp.db.AppDatabase;
import com.paunoski.manuel.todoapp.db.Todo;
import com.paunoski.manuel.todoapp.db.TodoDao;

public class EditActivity extends AppCompatLifecycleActivity {

    private Todo todo;
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        todo = (Todo) extras.get("todo");
        System.out.println(todo);

        editText = findViewById(R.id.edit_edit);
        Button submit = findViewById(R.id.btn_edit_submit);

        editText.setText(todo.text);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (!editText.getText().toString().equals(todo.text)) {
                    submit.setVisibility(View.VISIBLE);
                } else {
                    submit.setVisibility(View.INVISIBLE);
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    public void saveChanges(View view) {
        new AsyncTask<Todo, Void, Void>() {
            @Override
            protected Void doInBackground(Todo... todos) {
                todo.text = editText.getText().toString();
                TodoDao todoDao = AppDatabase.getInstance(getApplication()).todoDao();
                todoDao.update(todos);
                return null;
            }
        }.execute(todo);
        finish();
    }

    public void goBack(View view) {
        finish();
    }
}
