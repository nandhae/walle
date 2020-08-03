package com.tw.walle.controller;

import com.tw.walle.model.ToDo;
import com.tw.walle.service.ToDoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class ToDoController {

    @Autowired
    ToDoService toDoService;

    @PostMapping("/todo")
    public ResponseEntity<ToDo> addToDo(String name) {
        Optional<ToDo> toDoOptional = toDoService.addToDo(name);

        return toDoOptional.map(toDo -> ResponseEntity.ok().body(toDo))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
    }
}
