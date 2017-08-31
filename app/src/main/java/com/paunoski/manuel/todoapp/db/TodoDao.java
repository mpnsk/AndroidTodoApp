package com.paunoski.manuel.todoapp.db;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public abstract class TodoDao {

    @Query("SELECT * FROM todo")
    public abstract LiveData<List<Todo>> getAll();

    @Insert
    public abstract void insertAll(Todo... todos);

}
