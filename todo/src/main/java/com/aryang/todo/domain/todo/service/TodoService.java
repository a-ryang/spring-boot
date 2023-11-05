package com.aryang.todo.domain.todo.service;

import com.aryang.todo.domain.todo.controller.request.CreateTodoRequest;
import com.aryang.todo.domain.todo.controller.request.UpdateTodoRequest;
import com.aryang.todo.domain.todo.entity.Todo;
import com.aryang.todo.global.pagination.Page;
import com.aryang.todo.global.pagination.PageRequest;

public interface TodoService {

    Todo create(Long memberId, CreateTodoRequest payload);
    Page<Todo> getList(Long memberId, PageRequest page);
    Todo getById(Long id);
    Todo update(Long memberId, Long id , UpdateTodoRequest payload);
    Todo complete(Long memberId, Long id);
    Todo uncomplete(Long memberId, Long id);
    void delete(Long memberId, Long id);

}
