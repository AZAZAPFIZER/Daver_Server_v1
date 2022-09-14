package com.azazafizer.server_v1.api.category.controller;

import com.azazafizer.server_v1.api.category.domain.dto.ModifyCategoryDto;
import com.azazafizer.server_v1.api.category.domain.dto.SelectCategoryDto;
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

    @GetMapping
    public ResponseData<List<Category>> getAllCategory() {
        List<Category> categoryList = categoryService.getAllCategory();
        return new ResponseData<>(
                HttpStatus.OK,
                "모든 카테고리 조회 성공",
                categoryList
        );
    }

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
                categoryList
        );
    }

    @PostMapping("/select")
    public Response selectCategory(
            @RequestAttribute Member member,
            @RequestBody SelectCategoryDto dto
    ) {
        categoryService.selectCategory(member, dto);
        return new Response(
                HttpStatus.OK,
                "카테고리 선정 성공"
        );
    }

    @PostMapping
    public Response modifyCategory(
            @RequestBody ModifyCategoryDto dto
    ) {
        categoryService.modifyCategory(dto);
        return new Response(
                HttpStatus.OK,
                "카테고리 생성 성공"
        );
    }
}
