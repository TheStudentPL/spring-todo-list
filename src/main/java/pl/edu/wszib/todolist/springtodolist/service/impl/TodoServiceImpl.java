package pl.edu.wszib.todolist.springtodolist.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.wszib.todolist.springtodolist.configuration.dao.TodoDao;
import pl.edu.wszib.todolist.springtodolist.dto.TodoDTO;

import pl.edu.wszib.todolist.springtodolist.model.Status;
import pl.edu.wszib.todolist.springtodolist.model.Todo;
import pl.edu.wszib.todolist.springtodolist.service.TodoService;
import pl.edu.wszib.todolist.springtodolist.utils.ConverterComponent;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TodoServiceImpl implements TodoService {

    @Autowired // baza danych
    private TodoDao todoDao;

    //Dodajemy nasz komponent converterComponent
    @Autowired
    private ConverterComponent converterComponent;

    @Override
    public List<TodoDTO> findAll() {
        return  todoDao.findAll()
                .stream()
                .map(todo -> converterComponent.convert(todo))
                //.map(converterComponent::convert) inaczej: to co wyzej; funkcje z jedna zmienna moga byc jako lambda
                .collect(Collectors.toList());
    }

    @Override
    public TodoDTO add(TodoDTO dto) {
        Todo todo = converterComponent.convert(dto);
        todo.setStatus(Status.NEW);
        return converterComponent.convert(
                        todoDao.save(todo)
        );
    }

    @Override
    public TodoDTO find(Integer id) {
        return todoDao.findById(id)
                .map(converterComponent::convert)
                .orElse(null);
    }

    @Override
    public TodoDTO update(TodoDTO dto) {
        Todo todo = todoDao.findById(dto.id)
                .orElse(new Todo()); // moze byc Optionalem, idenpotentny robi wiecej niz moze. put vs post
        Todo converterd = converterComponent.convert(dto);
        todo.setStatus(converterd.getStatus());
        todo.setTitle(converterd.getTitle());
        todo.setDueDate(converterd.getDueDate());
        return converterComponent.convert(
                todoDao.save(todo));
    }

    @Override
    public TodoDTO delete(Integer id) {
        Optional<Todo> todo = todoDao.findById(id);
        todo.ifPresent(t -> todoDao.delete(t)); // jesli istnieje to wykonaj to usun
        return todo.map(converterComponent::convert) // przemapuj
                .orElse(null);
    }


}
