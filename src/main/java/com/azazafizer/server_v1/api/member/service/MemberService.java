package com.azazafizer.server_v1.api.member.service;

import com.azazafizer.server_v1.api.member.domain.dto.*;
import com.azazafizer.server_v1.api.member.domain.entity.Member;
import com.azazafizer.server_v1.api.member.domain.ro.LoginRo;
import com.azazafizer.server_v1.api.member.domain.ro.MemberRo;

public interface MemberService {

    Member getMemberById(String  id);

    void join(JoinDto joinDto);

    LoginRo login(LoginDto loginDto);

    void modifyMemberInfo(Member member, ModifyMemberDto dto);

    void modifyPw(Member member, ModifyPwDto dto);

    void authorizationPw(GetPwDto dto);

    MemberRo getMemberInfo();
}
