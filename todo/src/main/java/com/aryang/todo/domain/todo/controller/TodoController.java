package com.aryang.todo.domain.todo.controller;

import com.aryang.todo.domain.todo.controller.request.CreateTodoRequest;
import com.aryang.todo.domain.todo.controller.request.UpdateTodoRequest;
import com.aryang.todo.domain.todo.controller.response.TodoResponse;
import com.aryang.todo.domain.todo.entity.Todo;
import com.aryang.todo.domain.todo.service.TodoService;

import com.aryang.todo.global.pagination.Page;
import com.aryang.todo.global.pagination.PageRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/todos")
@RequiredArgsConstructor
@Validated
public class TodoController {

    private final TodoService service;
    
    @PostMapping
    public ResponseEntity<TodoResponse> create(
            @Valid @RequestBody final CreateTodoRequest body
        ) {
        Long tempMemberId = 1L;
        Todo todo = service.create(tempMemberId, body);
        return ResponseEntity.status(HttpStatus.CREATED).body(toResponse(todo));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TodoResponse> getById(
            @PathVariable @Min(1) Long id
    ) {
        Todo todo = service.getById(id);
        return ResponseEntity.status(HttpStatus.OK).body(toResponse(todo));
    }

    @GetMapping
    public ResponseEntity<Page<TodoResponse>> getPages(
            @Valid PageRequest pageRequest
    ) {
        Long tempMemberId = 1L;
        Page<Todo> todoPage = service.getList(tempMemberId, pageRequest);

        List<TodoResponse> responseList = todoPage.getItems().stream()
                .map(this::toResponse)
                .collect(Collectors.toList());

        Page<TodoResponse> responsePage = Page.<TodoResponse>builder()
                .items(responseList)
                .totalItems(todoPage.getTotalItems())
                .itemsPerPage(todoPage.getItemsPerPage())
                .currentPage(todoPage.getCurrentPage())
                .totalPages(todoPage.getTotalPages())
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(responsePage);
    }

    @PatchMapping("/{id}/complete")
    public ResponseEntity<TodoResponse> complete(
            @PathVariable @Min(1) Long id
    ) {
        Long tempMemberId = 1L;
        Todo todo = service.complete(tempMemberId, id);
        return ResponseEntity.status(HttpStatus.OK).body(toResponse(todo));
    }

    @PatchMapping("/{id}/uncomplete")
    public ResponseEntity<TodoResponse> uncomplete(
            @PathVariable @Min(1) Long id
    ) {
        Long tempMemberId = 1L;
        Todo todo = service.uncomplete(tempMemberId, id);
        return ResponseEntity.status(HttpStatus.OK).body(toResponse(todo));
    }

    @PatchMapping("/{id}/content")
    public ResponseEntity<TodoResponse> update(
            @PathVariable @Min(1) Long id,
            @Valid @RequestBody final UpdateTodoRequest body
    ) {
        Long tempMemberId = 1L;
        Todo todo = service.update(tempMemberId, id, body);
        return ResponseEntity.status(HttpStatus.OK).body(toResponse(todo));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable @Min(1) Long id
    ) {
        Long tempMemberId = 1L;
        service.delete(tempMemberId, id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    private TodoResponse toResponse(Todo todo) {
        return new TodoResponse(
                todo.getId(),
                todo.getContent(),
                todo.getIsDone()
        );
    }

}
