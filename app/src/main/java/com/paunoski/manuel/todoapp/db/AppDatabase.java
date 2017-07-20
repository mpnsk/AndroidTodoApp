package com.paunoski.manuel.todoapp.db;


import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.support.annotation.VisibleForTesting;

import java.util.concurrent.atomic.AtomicInteger;


@Database(entities = {Todo.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase instance;
    private static AtomicInteger nextId;

    public static synchronized AppDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context, AppDatabase.class, "db")
                    .allowMainThreadQueries() //TODO somewhere an AsyncTask needs to happen
                    .build();
        }
        return instance;
    }

    @VisibleForTesting
    public static void switchToInMemory(Context context) {
        instance = Room.inMemoryDatabaseBuilder(context, AppDatabase.class).build();
    }

    private static int getNextId(Context context) {
        if (nextId == null) {
            nextId = new AtomicInteger(getInstance(context).todoDao().getId());
        }
        return nextId.incrementAndGet();
    }

    public static Todo createTodo(Context context) {
        Todo todo = new Todo();
        todo.id = getNextId(context);
        return todo;
    }

    public abstract TodoDao todoDao();
}
