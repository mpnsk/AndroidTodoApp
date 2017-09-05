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

    LiveData<List<Todo>> getObservableTodos() {
        return observableTodos;
    }

    @SuppressLint("StaticFieldLeak")
    void insertTodo(Todo todo) {
        new AsyncTask<Todo, Void, Void>() {
            @Override
            protected Void doInBackground(Todo... todos) {
                todoDao.insertAll(todos);
                return null;
            }
        }.execute(todo);
    }

    void removeTodo(Todo todo) {
        new DeleteAsyncTask(todoDao).execute(todo);
    }

    private static class DeleteAsyncTask extends AsyncTask<Todo, Void, Void> {
        private final TodoDao todoDao;

        private DeleteAsyncTask(TodoDao todoDao) {
            this.todoDao = todoDao;
        }

        @Override
        protected Void doInBackground(final Todo... todos) {
            todoDao.delete(todos[0]);
            return null;
        }
    }
}
