package com.paunoski.manuel.todoapp;

import android.annotation.SuppressLint;
import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.paunoski.manuel.todoapp.db.AppDatabase;
import com.paunoski.manuel.todoapp.db.Todo;
import com.paunoski.manuel.todoapp.db.TodoDao;

import java.util.List;


public class TodoViewModel extends AndroidViewModel {

    private final TodoDao todoDao;
    private final LiveData<List<Todo>> observableTodos;

    public TodoViewModel(Application application) {
        super(application);
        todoDao = AppDatabase.getInstance(getApplication()).todoDao();
        observableTodos = todoDao.getAll();
    }

    public LiveData<List<Todo>> getObservableTodos() {
        return observableTodos;
    }

    @SuppressLint("StaticFieldLeak")
    public void insertTodo(Todo todo) {
        new AsyncTask<Todo, Void, Void>() {
            @Override
            protected Void doInBackground(Todo... todos) {
                todoDao.insertAll(todos);
                return null;
            }
        }.execute(todo);
    }
}
