package com.aryang.todo.domain.auth.service;

import com.aryang.todo.domain.auth.controller.request.SignUpRequest;

import com.aryang.todo.domain.auth.dao.AuthEmailDao;
import com.aryang.todo.domain.auth.entity.AuthEmail;
import com.aryang.todo.domain.member.dao.MemberDao;
import com.aryang.todo.domain.member.dao.MemberProfileDao;
import com.aryang.todo.domain.member.entity.Member;
import com.aryang.todo.domain.member.entity.MemberProfile;
import com.aryang.todo.domain.member.enums.LoginType;
import com.aryang.todo.global.exception.BusinessException;
import com.aryang.todo.global.exception.ErrorCode;
import com.aryang.todo.global.utils.crypto.CryptoUtil;
import lombok.RequiredArgsConstructor;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DefaultAuthService implements AuthService {

    private final AuthEmailDao authEmailDao;
    private final MemberDao memberDao;
    private final MemberProfileDao memberProfileDao;
    private final PasswordEncoder passwordEncoder;
    private final CryptoUtil cryptoUtil;

    @Override
    @Transactional
    public void signUp(SignUpRequest payload) {
        String encryptedEmail = cryptoUtil.encrypt(payload.email());

        // 이미 존재하는 이메일
        authEmailDao.findByEmail(encryptedEmail).ifPresent(e -> {
            throw new BusinessException(ErrorCode.ALREADY_EXISTS_EMAIL);
        });

        // 이미 존재하는 닉네임인지
        memberProfileDao.findByNickname(payload.nickname()).ifPresent(memberProfile -> {
            throw new BusinessException(ErrorCode.ALREADY_EXISTS_NICKNAME);
        });

        // 가입
        Member newMember = Member.builder()
                .loginType(LoginType.EMAIL)
                .build();
        memberDao.save(newMember);

        MemberProfile newMemberProfile = MemberProfile.builder()
                .id(newMember.getId())
                .nickname(payload.nickname())
                .build();
        memberProfileDao.save(newMemberProfile);

        AuthEmail newAuthEmail = AuthEmail.builder()
                .memberId(newMember.getId())
                .email(cryptoUtil.encrypt(encryptedEmail))
                .password(passwordEncoder.encode(payload.password()))
                .build();

        authEmailDao.save(newAuthEmail);
    }

}
