package com.azazafizer.server_v1.api.category.service;

import com.azazafizer.server_v1.api.category.domain.entity.Category;
import com.azazafizer.server_v1.api.category.domain.repository.CategoryRepository;
import com.azazafizer.server_v1.api.member.domain.entity.Member;
import com.azazafizer.server_v1.api.member.domain.repository.MemberRepository;
import com.azazafizer.server_v1.common.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService{

    private final CategoryRepository categoryRepository;
    private final MemberRepository memberRepository;

    @Override
    public List<Category> getMyCategory(Member member) {
        return categoryRepository.findByMember(member);
    }

    @Override
    public List<Category> getOtherPeopleCategory(int memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new NotFoundException("해당 회원은 존재히지 않습니다"));
        return categoryRepository.findByMember(member);
    }
}
