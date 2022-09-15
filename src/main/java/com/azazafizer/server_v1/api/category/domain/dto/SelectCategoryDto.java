package com.azazafizer.server_v1.api.category.domain.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class SelectCategoryDto {
    private List<String> categoryList;
}
