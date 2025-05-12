package org.isep.cleancode;

import static spark.Spark.*;

import org.isep.cleancode.application.TodoManager;
import org.isep.cleancode.dataPersistence.TodoRepository;
import org.isep.cleancode.presentation.TodoController;

public class Main {

    public static void main(String[] args) {
        port(4567);

        System.out.println("Serveur démarré (4567)");

        TodoRepository repository = new TodoRepository();
        TodoManager manager = new TodoManager(repository);
        TodoController controller = new TodoController(manager);

        get("/todos", controller::getAllTodos);

        post("/todos", controller::createTodo);
    }
}

