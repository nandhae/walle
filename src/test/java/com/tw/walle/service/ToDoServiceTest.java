package com.tw.walle.service;

import com.tw.walle.model.ToDo;
import com.tw.walle.repository.ToDoRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataAccessException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ToDoServiceTest {
    @InjectMocks
    ToDoService toDoService;

    @Mock
    ToDoRepository toDoRepository;

    @Test
    void shouldReturnOptionalOfToDoWhenToDoIsSavedSuccessfully() {
        final String todoName = "Be a super hero!";
        final ToDo toDo = ToDo
                .builder()
                .id(1)
                .name(todoName)
                .build();

        when(toDoRepository.save(any()))
                .thenReturn(toDo);

        final Optional<ToDo> toDoOptional = toDoService.addToDo(todoName);

        assertTrue(toDoOptional.isPresent());
        assertEquals(1, toDoOptional.get().getId());
        assertEquals(todoName, toDoOptional.get().getName());
    }

    @Test
    void shouldReturnOptionalOfEmptyWhenToDoIsSaveFails() {
        final String todoName = "Be a super hero!";
        final ToDo toDo = ToDo
                .builder()
                .id(1)
                .name(todoName)
                .build();

        when(toDoRepository.save(any()))
                .thenThrow(buildDataAccessException());

        final Optional<ToDo> toDoOptional = toDoService.addToDo(todoName);

        assertTrue(toDoOptional.isEmpty());
    }

    private DataAccessException buildDataAccessException() {
        return new DataAccessException("Save Failed") {
            @Override
            public String getMessage() {
                return super.getMessage();
            }
        };
    }


}
