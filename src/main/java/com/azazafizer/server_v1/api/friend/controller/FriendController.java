package com.azazafizer.server_v1.api.friend.controller;

import com.azazafizer.server_v1.api.friend.domain.entity.FriendRelation;
import com.azazafizer.server_v1.api.friend.service.FriendService;
import com.azazafizer.server_v1.api.member.domain.entity.Member;
import com.azazafizer.server_v1.common.annotation.AuthorizationCheck;
import com.azazafizer.server_v1.common.response.Response;
import com.azazafizer.server_v1.common.response.ResponseData;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/friend")
@RequiredArgsConstructor
public class FriendController {

    private final FriendService friendService;

    @AuthorizationCheck
    @GetMapping("/my")
    public ResponseData<List<FriendRelation>> getMyFriend(
            @RequestAttribute Member member
    ) {
        List<FriendRelation> friendList = friendService.getMyFriend(member);
        return new ResponseData<>(
                HttpStatus.OK,
                "자신의 친구 조회 성공",
                friendList
        );
    }

    @AuthorizationCheck
    @GetMapping("/recommend")
    public ResponseData<List<Member>> getRecommendFriend(
            @RequestAttribute Member member
    ) {
        List<Member> friendList = friendService.getRecommendFriend(member);
        return new ResponseData<>(
                HttpStatus.OK,
                "자신의 친구 조회 성공",
                friendList
        );
    }


    @AuthorizationCheck
    @PostMapping("/{memberId}")
    public Response addFriend(
            @RequestAttribute Member member,
            @PathVariable String memberId
    ) {
        friendService.addFriend(member, memberId);
        return new Response(
                HttpStatus.CREATED,
                "친구 신청 성공"
        );
    }

    @AuthorizationCheck
    @DeleteMapping("/cancel/{memberId}")
    public Response cancelAddFriend(
            @RequestAttribute Member member,
            @PathVariable String memberId
    ) {
        friendService.cancelAddFriend(member, memberId);
        return new Response(
                HttpStatus.OK,
                "친구 신청 취소 성공"
        );
    }

    @AuthorizationCheck
    @PostMapping("/allow/{memberId}")
    public Response allowFriend(
            @RequestAttribute Member member,
            @PathVariable String memberId
    ) {
        friendService.allowFriend(member, memberId);
        return new Response(
                HttpStatus.OK,
                "친구 신청 수락 성공"
        );
    }

    @AuthorizationCheck
    @PostMapping("/deny/{memberId}")
    public Response denyFriend(
            @RequestAttribute Member member,
            @PathVariable String memberId
    ) {
        friendService.denyFriend(member, memberId);
        return new Response(
                HttpStatus.OK,
                "친구 신청 거절 성공"
        );
    }
}
