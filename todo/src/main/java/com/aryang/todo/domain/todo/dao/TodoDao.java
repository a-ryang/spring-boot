package com.aryang.todo.domain.todo.dao;

import com.aryang.todo.domain.todo.entity.Todo;
import com.aryang.todo.global.pagination.PageRequest;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface TodoDao {

    int save(Todo todo);
    Optional<Todo> findById(Long id);
    List<Todo> findListByMemberId(Long memberId, PageRequest page);
    int countByMemberId(Long memberId);
    int update(Todo todo);
    int delete(Long id);

}
