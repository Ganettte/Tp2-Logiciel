package org.isep.cleancode.presentation;

import com.google.gson.Gson;

import org.isep.cleancode.Todo;
import org.isep.cleancode.application.TodoManager;

import org.isep.cleancode.dataPersistence.TodoRepository;
import spark.Request;
import spark.Response;

import java.util.List;


public class TodoController {
    private static final Gson gson = new Gson();
    private final TodoManager manager;

    public TodoController(TodoManager manager){
        this.manager=manager;
    }

    public Object getAllTodos(Request req, Response res) {
        res.type("application/json");
        return gson.toJson(manager.getTodos());
    };

    public Object createTodo(Request req, Response res) {

        String body = req.body();
        System.out.println("Requête reçue : " + body);

        Todo newTodo;
        try {
            newTodo = gson.fromJson(req.body(), Todo.class);
            manager.createTodo(newTodo);
            res.status(201);
            res.type("application/json");
            return gson.toJson(newTodo);

        } catch (IllegalArgumentException e){
            res.status(400);
            return e.getMessage();
        }

    };

    public static class ErrorResponse{
        String error;

        public ErrorResponse (String error){
            this.error=error;
        }
    };
}
