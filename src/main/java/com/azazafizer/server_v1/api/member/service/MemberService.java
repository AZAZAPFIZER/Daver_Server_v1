package com.azazafizer.server_v1.api.member.service;

import com.azazafizer.server_v1.api.member.domain.entity.Member;

public interface MemberService {

    Member getMemberById(String id);
}
