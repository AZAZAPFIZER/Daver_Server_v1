package com.azazafizer.server_v1.api.friend.service;

import com.azazafizer.server_v1.api.friend.domain.entity.FriendRelation;
import com.azazafizer.server_v1.api.member.domain.entity.Member;

import java.util.List;

public interface FriendService {

    List<FriendRelation> getMyFriend(Member member);

    void addFriend(Member member, String memberId);

    void cancelAddFriend(Member member, String memberId);

    void allowFriend(Member member, String  memberId);

    void denyFriend(Member member, String  memberId);
}
