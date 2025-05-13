package org.isep.cleancode.application;

import org.isep.cleancode.Todo;
import org.isep.cleancode.dataPersistence.TodoRepository;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

public class TodoManager {

    private final ITodoRepository repository;

    public TodoManager(ITodoRepository repository){
        this.repository=repository;
    }

    public List<Todo> getTodos() {
        return repository.getAllTodos();
    };

    public void createTodo(Todo newTodo) throws IllegalArgumentException {
        if (newTodo.getName()==null || newTodo.getName().isBlank())
        {
            throw new IllegalArgumentException("Le nom de la tache est obligatoire");
        }

        if (newTodo.getName().length() >= 64)
        {
            throw new IllegalArgumentException("Le nom de la tache doit faire moins de 64 caractèrs");
        }

        boolean nameExist = repository.getAllTodos().stream().anyMatch(todo -> todo.getName().equalsIgnoreCase(newTodo.getName()));
        if (nameExist)
        {
            throw new IllegalArgumentException("Le nom de la tache existe déjà");
        }

        if (newTodo.getDate() != null && !newTodo.getDate().isBlank())
        {
            try{
                LocalDate.parse(newTodo.getDate(), DateTimeFormatter.ISO_LOCAL_DATE);
            } catch (DateTimeParseException e){
                throw new IllegalArgumentException("La date n'est pas au bon format: yyyy-mm-dd");
            }
        }
        repository.addTodo(newTodo);
    };

}
