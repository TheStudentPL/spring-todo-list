package pl.edu.wszib.todolist.springtodolist.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.edu.wszib.todolist.springtodolist.dto.TodoDTO;
import pl.edu.wszib.todolist.springtodolist.service.TodoService;

import java.util.List;

//Controler ma byc tylko pilotem do serwisow, bazuje na gotowych metodach
@RestController
@RequestMapping ("/api") // (produces = application/xml) inna wersja, defaultowo json, biblioteka jackson

// zpliku todoservice.ts mapowanie sprawdzamy
public class TodoController {

    @Autowired // dokladamy service jako dependency, endpoint
    private TodoService todoService;

    @GetMapping("/todos")
    public List<TodoDTO> getAll() {
        return todoService.findAll();
    }

    @GetMapping("/todo/{id}")
    public TodoDTO get(@PathVariable int id){
        return todoService.find(id);
    }

    @DeleteMapping("/todo/{id}")
    public TodoDTO delete(@PathVariable int id){
        return todoService.delete(id);
    }

    @PostMapping("/todo")
    public TodoDTO add(@RequestBody TodoDTO todoDTO){
        return todoService.add(todoDTO);
    }

    @GetMapping("/todos/upcomming")
    public List<TodoDTO> upcoming(){
        return todoService.upcoming();
    }

}
