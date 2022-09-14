package com.azazafizer.server_v1.api.category.service;

import com.azazafizer.server_v1.api.category.domain.dto.SelectCategoryDto;
import com.azazafizer.server_v1.api.category.domain.entity.Category;
import com.azazafizer.server_v1.api.category.domain.entity.CategoryMember;
import com.azazafizer.server_v1.api.member.domain.entity.Member;

import java.util.List;

public interface CategoryService {

    List<Category> getAllCategory();

    List<CategoryMember> getMyCategory(Member member);

    List<CategoryMember> getOtherPeopleCategory(int memberId);

    void selectCategory(Member member, SelectCategoryDto dto);
}
