package com.paunoski.manuel.todoapp.db;


import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.support.annotation.VisibleForTesting;


@Database(entities = {Todo.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase instance;

    public static synchronized AppDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context, AppDatabase.class, "db")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }

    @VisibleForTesting
    public static AppDatabase switchToInMemory(Context context) {
        instance = Room.inMemoryDatabaseBuilder(context, AppDatabase.class).build();
        return instance;
    }

    public abstract TodoDao todoDao();
}
