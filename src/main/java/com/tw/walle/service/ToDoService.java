package com.tw.walle.service;

import com.tw.walle.model.ToDo;
import com.tw.walle.repository.ToDoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ToDoService {

    @Autowired
    ToDoRepository toDoRepository;

    public Optional<ToDo> addToDo(String name) {
        try {
            final ToDo savedToDo = toDoRepository.save(new ToDo(name));
            return Optional.of(savedToDo);
        } catch (DataAccessException exception) {
            exception.printStackTrace();
        }

        return Optional.empty();
    }
}
