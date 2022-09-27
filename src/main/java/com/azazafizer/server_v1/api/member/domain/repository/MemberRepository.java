package com.azazafizer.server_v1.api.member.domain.repository;

import com.azazafizer.server_v1.api.member.domain.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, String> {

    Optional<Member> findByIdAndPw(String id, String pw);

    Optional<Member> findByIdAndName(String id, String name);
}
