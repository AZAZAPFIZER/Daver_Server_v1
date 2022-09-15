package com.azazafizer.server_v1.api.category.domain.repository;

import com.azazafizer.server_v1.api.category.domain.entity.CategoryMember;
import com.azazafizer.server_v1.api.member.domain.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryMemberRepository extends JpaRepository<CategoryMember, Integer> {
    List<CategoryMember> findByMember(Member member);
}
