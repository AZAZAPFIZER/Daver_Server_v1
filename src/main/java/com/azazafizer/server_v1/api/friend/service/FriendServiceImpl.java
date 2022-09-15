package com.azazafizer.server_v1.api.friend.service;

import com.azazafizer.server_v1.api.friend.domain.dto.AddFriendDto;
import com.azazafizer.server_v1.api.friend.domain.dto.AllowFriendDto;
import com.azazafizer.server_v1.api.friend.domain.dto.CancelAddFriendDto;
import com.azazafizer.server_v1.api.friend.domain.entity.FriendRelation;
import com.azazafizer.server_v1.api.friend.domain.enums.FriendStatus;
import com.azazafizer.server_v1.api.friend.domain.repository.FriendRepository;
import com.azazafizer.server_v1.api.member.domain.entity.Member;
import com.azazafizer.server_v1.api.member.domain.repository.MemberRepository;
import com.azazafizer.server_v1.common.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FriendServiceImpl implements FriendService{

    private final FriendRepository friendRepository;
    private final MemberRepository memberRepository;

    @Override
    public List<FriendRelation> getMyFriend(Member member) {
        return friendRepository.findByMyAndStatus(member, FriendStatus.FRIEND);
    }

    @Override
    public void addFriend(Member member, AddFriendDto dto) {
        Member friendMember = memberRepository.findById(dto.getMemberId())
                .orElseThrow(() -> new NotFoundException("해당 회원은 존재하지 않습니다"));
        FriendRelation friendRelation = FriendRelation.builder()
                .my(member)
                .friend(friendMember)
                .status(FriendStatus.PENDING)
                .build();
        friendRepository.save(friendRelation);
    }

    @Override
    public void cancelAddFriend(Member member, CancelAddFriendDto dto) {
        Member friendMember = memberRepository.findById(dto.getMemberId())
                .orElseThrow(() -> new NotFoundException("해당 회원은 존재하지 않습니다"));
        FriendRelation friendRelation = friendRepository.findByMyAndFriend(member, friendMember)
                .orElseThrow(() -> new NotFoundException("데이터가 존재하지 않습니다"));
        friendRepository.delete(friendRelation);
    }

    @Override
    public void allowFriend(Member member, AllowFriendDto dto) {
        Member friendMember = memberRepository.findById(dto.getMemberId())
                .orElseThrow(() -> new NotFoundException("해당 회원은 존재하지 않습니다"));
        FriendRelation friendRelation = friendRepository.findByMyAndFriend(friendMember, member)
                .orElseThrow(() -> new NotFoundException("데이터가 존재하지 않습니다"));
        friendRelation.updateStatus(FriendStatus.FRIEND);
        friendRepository.save(friendRelation);

        FriendRelation myFriendRelation = FriendRelation.builder()
                .my(member)
                .friend(friendMember)
                .status(FriendStatus.FRIEND)
                .build();
        friendRepository.save(myFriendRelation);
    }
}
