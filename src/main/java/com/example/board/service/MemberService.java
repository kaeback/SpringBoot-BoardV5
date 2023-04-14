package com.example.board.service;

import com.example.board.model.member.Member;
import com.example.board.repository.MemberMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class MemberService {

    // 데이터베이스 접근을 위한 MemberMapper 필드 선언
    private final MemberMapper memberMapper;

    // 회원가입
    public void saveMember(Member member) {
        memberMapper.saveMember(member);
    }

    public Member findMember(String member_id) {
        return memberMapper.findMember(member_id);
    }
}
