package com.azazafizer.server_v1.api.category.domain.repository;

import com.azazafizer.server_v1.api.category.domain.entity.Category;
import com.azazafizer.server_v1.api.member.domain.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

    List<Category> findByMember(Member member);
}
