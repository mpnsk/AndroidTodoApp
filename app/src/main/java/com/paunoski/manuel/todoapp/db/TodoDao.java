package com.paunoski.manuel.todoapp.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.content.Context;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Dao
public abstract class TodoDao {
    private static AtomicInteger nextId;

    private static int getNextId(Context context) {
        if (nextId == null) {
            nextId = new AtomicInteger(AppDatabase.getInstance(context).todoDao().getId());
        }
        return nextId.incrementAndGet();
    }

    public static Todo createTodo(Context context) {
        Todo todo = new Todo();
        todo.id = getNextId(context);
        return todo;
    }

    @Query("SELECT * FROM todo")
    public abstract List<Todo> getAll();

    @Insert
    public abstract void insertAll(Todo... todos);

    @Query("SELECT MAX(id) FROM todo")
    public abstract int getId();

}
