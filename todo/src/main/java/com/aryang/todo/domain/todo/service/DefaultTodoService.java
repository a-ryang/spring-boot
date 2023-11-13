package com.aryang.todo.domain.todo.service;

import com.aryang.todo.domain.todo.controller.request.CreateTodoRequest;
import com.aryang.todo.domain.todo.controller.request.UpdateTodoRequest;
import com.aryang.todo.domain.todo.dao.TodoDao;
import com.aryang.todo.domain.todo.entity.Todo;

import com.aryang.todo.global.exception.BusinessException;
import com.aryang.todo.global.exception.ErrorCode;
import com.aryang.todo.global.pagination.Page;
import com.aryang.todo.global.pagination.PageRequest;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DefaultTodoService implements TodoService {

    private final TodoDao dao;

    @Override
    public Todo create(Long memberId, CreateTodoRequest payload) {
        Todo newTodo = Todo.builder()
                .memberId(memberId)
                .content(payload.content())
                .isDone(false)
                .build();

        dao.save(newTodo);

        return newTodo;
    }

    @Override
    public Page<Todo> getList(Long memberId, PageRequest page) {
        List<Todo> todoList = dao.findListByMemberId(memberId, page);
        int todoCounts = dao.countByMemberId(memberId);
        int totalPages = (int) Math.ceil((double) todoCounts / page.getSize());
        int currentPage = page.getPage();

        return Page.<Todo>builder()
                .items(todoList)
                .totalItems(todoCounts)
                .itemsPerPage(page.getSize())
                .currentPage(currentPage)
                .totalPages(totalPages)
                .build();
    }

    @Override
    public Todo getById(Long id) {
        return dao.findById(id).orElseThrow(() -> new BusinessException(ErrorCode.TODO_NOT_FOUND));
    }

    @Override
    public Todo update(Long memberId, Long id, UpdateTodoRequest payload) {
        Todo foundTodo = getById(id);
        checkMemberAccess(memberId, foundTodo);

        foundTodo.update(payload.content());
        int result = dao.update(foundTodo);

        if(result == 0) throw new BusinessException(ErrorCode.TODO_NOT_FOUND);

        return foundTodo;
    }

    @Override
    public Todo complete(Long memberId, Long id) {
        Todo foundTodo = getById(id);
        checkMemberAccess(memberId, foundTodo);

        foundTodo.complete();
        int result = dao.update(foundTodo);

        if(result == 0) throw new BusinessException(ErrorCode.TODO_NOT_FOUND);

        return foundTodo;
    }

    @Override
    public Todo uncomplete(Long memberId, Long id) {
        Todo foundTodo = getById(id);
        checkMemberAccess(memberId, foundTodo);

        foundTodo.uncomplete();
        int result = dao.update(foundTodo);

        if(result == 0) throw new BusinessException(ErrorCode.TODO_NOT_FOUND);

        return foundTodo;
    }

    @Override
    public void delete(Long memberId, Long id) {
        Todo foundTodo = getById(id);
        checkMemberAccess(memberId, foundTodo);

        int result = dao.delete(id);
        if(result == 0) throw new BusinessException(ErrorCode.TODO_NOT_FOUND);
    }

    private void checkMemberAccess(Long memberId, Todo todo) {
        if (!todo.getMemberId().equals(memberId)) {
            throw new BusinessException(ErrorCode.UNAUTHORIZED_ACCESS);
        }
    }

}
