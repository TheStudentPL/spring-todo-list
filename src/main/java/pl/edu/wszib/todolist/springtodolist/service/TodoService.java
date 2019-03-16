package pl.edu.wszib.todolist.springtodolist.service;

import pl.edu.wszib.todolist.springtodolist.dto.TodoDTO;
import pl.edu.wszib.todolist.springtodolist.model.Status;

import java.util.List;

public interface TodoService {
     // przyjmujemy DTO i zwracamy DTO

    List<TodoDTO> findAll();
    TodoDTO add(TodoDTO dto);
    TodoDTO find(Integer id);
    TodoDTO update(TodoDTO dto);
    TodoDTO delete(Integer id);
    List<TodoDTO> upcoming();
    int count(Status status);
}
