package com.paunoski.manuel.todoapp.db;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import lombok.EqualsAndHashCode;
import lombok.ToString;


@Entity
@ToString
@EqualsAndHashCode
public class Todo {

    @PrimaryKey(autoGenerate = true)
    public int id;
    public String text;
    public boolean done;

}
