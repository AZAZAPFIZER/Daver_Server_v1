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
    @PostMapping("/{memberId}")
    public Response<C> addFriend(
            @RequestAttribute Member member,
            @PathVariable int memberId
    ) {
        friendService.addFriend(member, memberId);
        return new Response<C>(
                HttpStatus.CREATED,
                "친구 신청 성공"
        );
    }

    @DeleteMapping("/cancel/{memberId}")
    public Response<C> cancelAddFriend(
            @RequestAttribute Member member,
            @PathVariable int memberId
    ) {
        friendService.cancelAddFriend(member, memberId);
        return new Response<C>(
                HttpStatus.OK,
                "친구 신청 취소 성공"
        );
    }

    @PostMapping("/allow/{memberId}")
    public Response<C> allowFriend(
            @RequestAttribute Member member,
            @PathVariable int memberId
    ) {
        friendService.allowFriend(member, memberId);
        return new Response<C>(
                HttpStatus.OK,
                "친구 신청 수락 성공"
        );
    }

    @PostMapping("/deny/{memberId}")
    public Response<C> denyFriend(
            @RequestAttribute Member member,
            @PathVariable int memberId
    ) {
        friendService.denyFriend(member, memberId);
        return new Response<C>(
                HttpStatus.OK,
                "친구 신청 거절 성공"
        );
    }
}
