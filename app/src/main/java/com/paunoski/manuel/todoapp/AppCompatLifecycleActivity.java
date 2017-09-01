package com.paunoski.manuel.todoapp;

import android.arch.lifecycle.LifecycleRegistry;
import android.arch.lifecycle.LifecycleRegistryOwner;
import android.support.v7.app.AppCompatActivity;

/**
 * A combination of {@link AppCompatActivity} and {@link android.arch.lifecycle.LifecycleActivity}.<br>
 * "This class is a temporary implementation detail until Lifecycles are integrated with support
 * library."
 */
public class AppCompatLifecycleActivity extends AppCompatActivity implements LifecycleRegistryOwner {

    private final LifecycleRegistry mRegistry = new LifecycleRegistry(this);

    @Override
    public LifecycleRegistry getLifecycle() {
        return mRegistry;
    }
}