package com.paunoski.manuel.todoapp;

import lombok.Data;
import lombok.NonNull;

@Data
class Todo {
    @NonNull
    private String name;
}
