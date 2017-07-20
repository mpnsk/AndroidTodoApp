package com.paunoski.manuel.todoapp;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.paunoski.manuel.todoapp.db.AppDatabase;
import com.paunoski.manuel.todoapp.db.Todo;
import com.paunoski.manuel.todoapp.db.TodoDao;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItem;

@RunWith(AndroidJUnit4.class)
public class SimpleEntityReadWriteTest {
    private TodoDao todoDao;
    private AppDatabase mDb;
    private Context context;

    @Before
    public void createDb() {
        context = InstrumentationRegistry.getTargetContext();
        mDb = Room.inMemoryDatabaseBuilder(context, AppDatabase.class).build();
        AppDatabase.switchToInMemory(context);
        mDb = AppDatabase.getInstance(context);
        todoDao = mDb.todoDao();
    }

    @After
    public void closeDb() throws IOException {
        mDb.close();
    }

    @Test
    public void writeUserAndReadInList() throws Exception {
        Todo todo = AppDatabase.createTodo(context);
        todo.text = "my text";
        todoDao.insertAll(todo, AppDatabase.createTodo(context), AppDatabase.createTodo(context));
        List<Todo> all = todoDao.getAll();
        assertThat(all, hasItem(todo));
    }
}