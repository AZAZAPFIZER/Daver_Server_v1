package com.azazafizer.server_v1.api.member.domain.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Entity(name = "member")
@NoArgsConstructor
public class Member {

    @Id
    private String id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String pw;

    @Column(nullable = false)
    private String email;

    private String profileImage;

    @Column(nullable = false)
    private LocalDate joinedAt;

    @Builder
    public Member(String id, String name, String pw, String email) {
        this.id = id;
        this.name = name;
        this.pw = pw;
        this.email = email;
        this.joinedAt = LocalDate.now();
    }
}
