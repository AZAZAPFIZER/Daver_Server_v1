package com.azazafizer.server_v1.api.member.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @JsonIgnore
    private String pw;

    private String profileImage;

    @Column(nullable = false)
    private LocalDate joinedAt;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private String x;

    @Column(nullable = false)
    private String y;

    public void updateMemberInfo(String name, String profileImage) {
        this.name = name;
        this.profileImage = profileImage;
    }

    public void updatePw(String pw) {
        this.pw = pw;
    }

    @Builder
    public Member(String id , String name, String pw, String address, String x, String y) {
        this.id = id;
        this.name = name;
        this.pw = pw;
        this.joinedAt = LocalDate.now();
        this.address = address;
        this.x = x;
        this.y = y;
    }
}
