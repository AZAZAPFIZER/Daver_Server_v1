package com.azazafizer.server_v1.api.friend.controller;

import com.azazafizer.server_v1.api.friend.domain.dto.AddFriendDto;
import com.azazafizer.server_v1.api.friend.domain.entity.Friend;
import com.azazafizer.server_v1.api.friend.service.FriendService;
import com.azazafizer.server_v1.api.member.domain.entity.Member;
import com.azazafizer.server_v1.common.response.Response;
import com.azazafizer.server_v1.common.response.ResponseData;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/friend")
@RequiredArgsConstructor
public class FriendController {

    private final FriendService friendService;

    @GetMapping("/my")
    public ResponseData<List<Friend>> getMyFriend(
            @RequestAttribute Member member
    ) {
        List<Friend> friendList = friendService.getMyFriend(member);
        return new ResponseData<>(
                HttpStatus.OK,
                "자신의 친구 조회 성공",
                friendList
        );
    }

    @PostMapping
    public Response addFriend(
            @RequestAttribute Member member,
            @RequestBody @Valid AddFriendDto dto
    ) {
        friendService.addFriend(member, dto);
        return new Response(
                HttpStatus.OK,
                "친구 신청 성공"
        );
    }
}
