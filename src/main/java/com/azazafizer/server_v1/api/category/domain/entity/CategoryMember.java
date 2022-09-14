package com.azazafizer.server_v1.api.category.domain.entity;

import com.azazafizer.server_v1.api.member.domain.entity.Member;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity(name = "category_member")
@NoArgsConstructor
public class CategoryMember {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "fk_member_id")
    @JsonIgnore
    private Member member;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "fk_category_id")
    private Category category;
}
