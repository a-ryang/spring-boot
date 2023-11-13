package com.aryang.todo.domain.member.dao;

import com.aryang.todo.domain.member.entity.MemberProfile;
import org.apache.ibatis.annotations.Mapper;

import java.util.Optional;

@Mapper
public interface MemberProfileDao {

    int save(MemberProfile memberProfile);
    Optional<MemberProfile> findByNickname(String nickname);

}
