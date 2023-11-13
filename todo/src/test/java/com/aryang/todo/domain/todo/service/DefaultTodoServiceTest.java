package com.aryang.todo.domain.todo.service;

import com.aryang.todo.domain.todo.controller.request.CreateTodoRequest;
import com.aryang.todo.domain.todo.controller.request.UpdateTodoRequest;
import com.aryang.todo.domain.todo.dao.TodoDao;
import com.aryang.todo.domain.todo.entity.Todo;

import com.aryang.todo.global.exception.BusinessException;
import com.aryang.todo.global.exception.ErrorCode;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DefaultTodoServiceTest {

    @InjectMocks
    private DefaultTodoService service;

    @Mock
    private TodoDao dao;

    @Test
    void todo를_생성한다() {
        final Long memberId = 1L;
        final String content = "test content";
        CreateTodoRequest payload = new CreateTodoRequest(content);

        when(dao.save(any(Todo.class))).thenReturn(1);

        Todo todo = service.create(memberId, payload);

        // 생성된 todo가 null이 아닌지 확인
        assertNotNull(todo);

        // 생성된 todo의 속성 확인
        assertEquals(memberId, todo.getMemberId());
        assertEquals(content, todo.getContent());
        assertFalse(todo.getIsDone());
    }

    @Test
    @DisplayName("id로 할 일을 가져온다")
    void getByIdTest() {
        Todo existingTodo = new Todo(1L, 1L, "content", false);
        when(dao.findById(1L)).thenReturn(Optional.of(existingTodo));

        Todo foundTodo = service.getById(1L);

        assertNotNull(foundTodo);
        assertEquals(1L, foundTodo.getId());
        assertEquals("content", foundTodo.getContent());
    }

    @Test
    @DisplayName("id로 할 일 찾을 수 없다면, not found 예외를 던진다")
    void getByIdWhenNotExistsIdTest(){
        when(dao.findById(anyLong())).thenReturn(Optional.empty());

        BusinessException exception = assertThrows(BusinessException.class, () -> service.getById(1L));
        assertEquals(ErrorCode.TODO_NOT_FOUND.getMessage(), exception.getMessage());
    }

    @Test
    @DisplayName("할 일을 업데이트한다")
    void updateTodo() {
        Todo existingTodo = new Todo(1L, 1L, "content", false);
        when(dao.findById(1L)).thenReturn(Optional.of(existingTodo));
        when(dao.update(any(Todo.class))).thenReturn(1);

        UpdateTodoRequest payload = new UpdateTodoRequest("updated content");

        Todo updatedTodo = service.update(1L, 1L, payload);

        assertNotNull(updatedTodo);
        assertEquals("updated content", updatedTodo.getContent());
    }

    @Test
    @DisplayName("업데이트 도중 id로 할 일 찾을 수 없다면, not found 예외를 던진다")
    void updateTodoWhenNotExistsIdTest() {
        when(dao.findById(anyLong())).thenReturn(Optional.empty());

        UpdateTodoRequest payload = new UpdateTodoRequest("updated content");

        BusinessException exception = assertThrows(BusinessException.class, () -> service.update(1L, 1L, payload));
        assertEquals(ErrorCode.TODO_NOT_FOUND.getMessage(), exception.getMessage());
    }

    @Test
    @DisplayName("업데이트 도중 사용자 id가 다르다면 unauthorized 예외를 던진다")
    void updateTodoWhenDifferentMemberTest() {
        Long differentMemberId = 2L;
        Todo existingTodo = new Todo(1L, 1L, "content", false);
        when(dao.findById(1L)).thenReturn(Optional.of(existingTodo));
        UpdateTodoRequest payload = new UpdateTodoRequest("updated content");

        BusinessException exception = assertThrows(BusinessException.class, () -> service.update(differentMemberId, 1L, payload));
        assertEquals(ErrorCode.UNAUTHORIZED_ACCESS.getMessage(), exception.getMessage());
    }

}