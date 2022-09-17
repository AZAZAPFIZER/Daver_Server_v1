package com.azazafizer.server_v1.api.friend.service;

import com.azazafizer.server_v1.api.friend.domain.entity.FriendRelation;
import com.azazafizer.server_v1.api.friend.domain.enums.FriendStatus;
import com.azazafizer.server_v1.api.friend.domain.repository.FriendRelationRepository;
import com.azazafizer.server_v1.api.member.domain.entity.Member;
import com.azazafizer.server_v1.api.member.domain.repository.MemberRepository;
import com.azazafizer.server_v1.common.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FriendServiceImpl implements FriendService{

    private final FriendRelationRepository friendRelationRepository;
    private final MemberRepository memberRepository;

    @Override
    public List<FriendRelation> getMyFriend(Member member) {
        return friendRelationRepository.findByMyAndStatus(member, FriendStatus.FRIEND);
    }

    @Override
    public void addFriend(Member member, int memberId) {
        Member friendMember = memberRepository.findById(memberId)
                .orElseThrow(() -> new NotFoundException("해당 회원은 존재하지 않습니다"));
        FriendRelation friendRelation = FriendRelation.builder()
                .my(member)
                .friend(friendMember)
                .status(FriendStatus.PENDING)
                .build();
        friendRelationRepository.save(friendRelation);
    }

    @Override
    public void cancelAddFriend(Member member, int memberId) {
        Member friendMember = memberRepository.findById(memberId)
                .orElseThrow(() -> new NotFoundException("해당 회원은 존재하지 않습니다"));
        FriendRelation friendRelation = friendRelationRepository.findByMyAndFriend(member, friendMember)
                .orElseThrow(() -> new NotFoundException("데이터가 존재하지 않습니다"));
        friendRelationRepository.delete(friendRelation);
    }

    @Override
    public void allowFriend(Member member, int memberId) {
        Member friendMember = memberRepository.findById(memberId)
                .orElseThrow(() -> new NotFoundException("해당 회원은 존재하지 않습니다"));
        FriendRelation friendRelation = friendRelationRepository.findByMyAndFriend(friendMember, member)
                .orElseThrow(() -> new NotFoundException("데이터가 존재하지 않습니다"));
        friendRelation.updateStatus(FriendStatus.FRIEND);
        friendRelationRepository.save(friendRelation);

        FriendRelation myFriendRelation = FriendRelation.builder()
                .my(member)
                .friend(friendMember)
                .status(FriendStatus.FRIEND)
                .build();
        friendRelationRepository.save(myFriendRelation);
    }

    @Override
    public void denyFriend(Member member, int memberId) {
        Member friendMember = memberRepository.findById(memberId)
                .orElseThrow(() -> new NotFoundException("해당 회원은 존재하지 않습니다"));
        FriendRelation friendRelation = friendRelationRepository.findByMyAndFriend(friendMember, member)
                .orElseThrow(() -> new NotFoundException("데이터가 존재하지 않습니다"));
        FriendRelation myRelation = friendRelationRepository.findByMyAndFriend(friendMember, member)
                .orElseThrow(() -> new NotFoundException("데이터가 존재하지 않습니다"));
        friendRelationRepository.delete(friendRelation);
        friendRelationRepository.delete(myRelation);
    }
}
