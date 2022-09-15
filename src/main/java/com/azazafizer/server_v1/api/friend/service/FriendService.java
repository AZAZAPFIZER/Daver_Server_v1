package com.azazafizer.server_v1.api.friend.service;

import com.azazafizer.server_v1.api.friend.domain.entity.Friend;
import com.azazafizer.server_v1.api.member.domain.entity.Member;

import java.util.List;

public interface FriendService {

    List<Friend> getMyFriend(Member member);
}
