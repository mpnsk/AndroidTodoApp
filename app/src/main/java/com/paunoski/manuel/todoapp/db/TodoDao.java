package com.paunoski.manuel.todoapp.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface TodoDao {
    @Query("SELECT * FROM todo")
    List<Todo> getAll();

    @Insert
    void insertAll(Todo... todos);

    @Query("SELECT MAX(id) FROM todo")
    int getId();
}
