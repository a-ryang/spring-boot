package com.aryang.todo.domain.member.dao;

import com.aryang.todo.domain.member.entity.Member;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberDao {

    int save(Member member);

}
