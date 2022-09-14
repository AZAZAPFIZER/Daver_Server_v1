package com.azazafizer.server_v1.api.member.service;

import com.azazafizer.server_v1.api.member.domain.dto.JoinDto;
import com.azazafizer.server_v1.api.member.domain.dto.LoginDto;
import com.azazafizer.server_v1.api.member.domain.dto.ModifyMemberDto;
import com.azazafizer.server_v1.api.member.domain.entity.Member;
import com.azazafizer.server_v1.api.member.domain.ro.LoginRo;

public interface MemberService {

    Member getMemberById(int id);

    void join(JoinDto joinDto);

    LoginRo login(LoginDto loginDto);

    void modifyMemberInfo(Member member, ModifyMemberDto dto);
}
