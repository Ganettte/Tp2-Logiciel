package org.isep.cleancode.dataPersistence;

import org.isep.cleancode.Todo;
import org.isep.cleancode.application.ITodoRepository;

import java.util.ArrayList;
import java.util.List;

public class TodoRepository implements ITodoRepository {
    private final List<Todo> todos = new ArrayList<>();

    @Override
    public List<Todo> getAllTodos() {
    return todos;
    };

    @Override
    public void addTodo(Todo newTodo) {
        todos.add(newTodo);
    };

}
