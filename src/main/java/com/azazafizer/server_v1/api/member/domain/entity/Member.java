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

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String pw;

    @Column(nullable = false)
    private String email;

    private String profileImage;

    @Column(nullable = false)
    private LocalDate joinedAt;

    public void updateMemberInfo(String name, String email, String profileImage){
        this.name = name;
        this.email = email;
        this.profileImage = profileImage;
    }

    @Builder
    public Member(String name, String pw, String email) {
        this.name = name;
        this.pw = pw;
        this.email = email;
        this.joinedAt = LocalDate.now();
    }
}
