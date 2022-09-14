package com.azazafizer.server_v1.api.category.controller;

import com.azazafizer.server_v1.api.category.domain.entity.Category;
import com.azazafizer.server_v1.api.category.service.CategoryService;
import com.azazafizer.server_v1.api.member.domain.entity.Member;
import com.azazafizer.server_v1.common.response.Response;
import com.azazafizer.server_v1.common.response.ResponseData;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/category")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping("/my")
    public ResponseData<List<Category>> getMyCategory(
            @RequestAttribute Member member
    ) {
        List<Category> categoryList = categoryService.getMyCategory(member);
        return new ResponseData<>(
                HttpStatus.OK,
                "자신의 카테고리 조회 성공",
                categoryList
        );
    }
}
