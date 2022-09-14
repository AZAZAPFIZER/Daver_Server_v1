package com.azazafizer.server_v1.api.category.domain.entity;

import com.azazafizer.server_v1.api.member.domain.entity.Member;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity(name = "category")
@NoArgsConstructor
public class Category {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String name;

    @Builder
    public Category(String name) {
        this.name = name;
    }
}
