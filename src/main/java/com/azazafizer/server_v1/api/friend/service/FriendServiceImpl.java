package com.azazafizer.server_v1.api.friend.service;

import com.azazafizer.server_v1.api.friend.domain.entity.Friend;
import com.azazafizer.server_v1.api.friend.domain.repository.FriendRepository;
import com.azazafizer.server_v1.api.member.domain.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FriendServiceImpl implements FriendService{

    private final FriendRepository friendRepository;

    @Override
    public List<Friend> getMyFriend(Member member) {
        return friendRepository.findByMy(member);
    }
}
