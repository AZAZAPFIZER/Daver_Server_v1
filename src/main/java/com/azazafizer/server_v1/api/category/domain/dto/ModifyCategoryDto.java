package com.azazafizer.server_v1.api.category.domain.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ModifyCategoryDto {

    private int categoryId;
    private String category;
}
