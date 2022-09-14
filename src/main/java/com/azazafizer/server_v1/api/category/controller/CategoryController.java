package com.azazafizer.server_v1.api.category.controller;

import com.azazafizer.server_v1.api.category.domain.entity.Category;
import com.azazafizer.server_v1.api.category.domain.entity.CategoryMember;
import com.azazafizer.server_v1.api.category.service.CategoryService;
import com.azazafizer.server_v1.api.member.domain.entity.Member;
import com.azazafizer.server_v1.common.response.Response;
import com.azazafizer.server_v1.common.response.ResponseData;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/category")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping("/my")
    public ResponseData<List<CategoryMember>> getMyCategory(
            @RequestAttribute Member member
    ) {
        List<CategoryMember> categoryList = categoryService.getMyCategory(member);
        return new ResponseData<>(
                HttpStatus.OK,
                "자신의 카테고리 조회 성공",
                categoryList
        );
    }

    @GetMapping("/{memberId}")
    public ResponseData<List<CategoryMember>> getOtherMemberCategory(
            @PathVariable int memberId
    ) {
        List<CategoryMember> categoryList = categoryService.getOtherPeopleCategory(memberId);
        return new ResponseData<>(
                HttpStatus.OK,
                "해당 사람의 카테고리 조회 성공",
                null
        );
    }
}
