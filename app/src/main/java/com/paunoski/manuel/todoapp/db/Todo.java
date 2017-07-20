package com.paunoski.manuel.todoapp.db;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@EqualsAndHashCode
@ToString
public class Todo {

    @PrimaryKey
    public int id;
    public String text;

}
