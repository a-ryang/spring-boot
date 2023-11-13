package com.aryang.todo.domain.auth.dao;

import com.aryang.todo.domain.auth.entity.AuthEmail;
import org.apache.ibatis.annotations.Mapper;

import java.util.Optional;

@Mapper
public interface AuthEmailDao {

    int save(AuthEmail authEmail);
    Optional<AuthEmail> findByEmail(String email);

}
