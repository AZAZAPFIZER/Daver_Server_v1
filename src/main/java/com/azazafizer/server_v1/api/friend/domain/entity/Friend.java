package com.azazafizer.server_v1.api.friend.domain.entity;

import com.azazafizer.server_v1.api.friend.domain.enums.FriendStatus;
import com.azazafizer.server_v1.api.member.domain.entity.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity(name = "friend")
@NoArgsConstructor
public class Friend {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "my_member_id")
    private Member my;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "friend_member_id")
    private Member friend;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private FriendStatus status;

    public void updateStatus(FriendStatus status) {
        this.status = status;
    }

    @Builder
    public Friend(Member my, Member friend, FriendStatus status) {
        this.my = my;
        this.friend = friend;
        this.status = status;
    }
}
