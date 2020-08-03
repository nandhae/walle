package com.tw.walle.controller;

import com.tw.walle.model.ToDo;
import com.tw.walle.service.ToDoService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.http.HttpStatus.BAD_REQUEST;

@ExtendWith(MockitoExtension.class)
class ToDoControllerTest {
    @InjectMocks
    ToDoController toDoController;

    @Mock
    ToDoService toDoService;

    @Test
    void shouldRespondWithSuccessWhenToDoIsAdded() {
        final String todoName = "Be a super hero!";
        final ToDo toDo = ToDo
                .builder()
                .id(1)
                .name(todoName)
                .build();

        when(toDoService.addToDo(todoName))
                .thenReturn(Optional.of(toDo));

        final ResponseEntity<ToDo> responseEntity = toDoController.addToDo(todoName);

        assertEquals(200, responseEntity.getStatusCodeValue());
        assertEquals(toDo, responseEntity.getBody());
    }

    @Test
    void shouldRespondWithBadRequestWhenToDoSaveFails() {
        final String todoName = "Be a super hero!";

        when(toDoService.addToDo(todoName))
                .thenReturn(Optional.empty());

        final ResponseEntity<ToDo> responseEntity = toDoController.addToDo(todoName);

        assertEquals(BAD_REQUEST, responseEntity.getStatusCode());
    }
}
