package com.azazafizer.server_v1.api.category.service;

import com.azazafizer.server_v1.api.category.domain.dto.SelectCategoryDto;
import com.azazafizer.server_v1.api.category.domain.entity.Category;
import com.azazafizer.server_v1.api.category.domain.entity.CategoryMember;
import com.azazafizer.server_v1.api.category.domain.repository.CategoryMemberRepository;
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
    private final CategoryMemberRepository categoryMemberRepository;
    private final MemberRepository memberRepository;

    @Override
    public List<Category> getAllCategory() {
        return categoryRepository.findAll();
    }

    @Override
    public List<CategoryMember> getMyCategory(Member member) {
        return categoryMemberRepository.findByMember(member);
    }

    @Override
    public List<CategoryMember> getOtherPeopleCategory(int memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new NotFoundException("해당 회원은 존재히지 않습니다"));
        return categoryMemberRepository.findByMember(member);
    }

    @Override
    public void selectCategory(Member member, SelectCategoryDto dto) {
        for(String i : dto.getCategoryList()) {
            Category category = categoryRepository.findByName(i)
                    .orElseGet(() -> categoryRepository.save(Category.builder()
                            .name(i)
                            .build()));
            CategoryMember categoryMember = CategoryMember.builder()
                    .member(member)
                    .category(category)
                    .build();
            categoryMemberRepository.save(categoryMember);
        }
    }
}
