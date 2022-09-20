package com.azazafizer.server_v1.api.chat.controller;

import com.azazafizer.server_v1.api.chat.domain.ChatRoom;
import com.azazafizer.server_v1.api.chat.service.ChatService;
import com.azazafizer.server_v1.common.response.ResponseData;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/chat")
@RequiredArgsConstructor
public class ChatController {

    private final ChatService chatService;

    @PostMapping
    public ResponseData<ChatRoom> createRoom(@RequestParam String name) {
        ChatRoom chatRoom = chatService.createRoom(name);
        return new ResponseData<>(
                HttpStatus.OK,
                "방만들기 성공",
                chatRoom
        );
    }

    @GetMapping("/room/test")
    public ResponseData<List<ChatRoom>> findAllRoom() {
        List<ChatRoom> chatRoomList = chatService.findAllRoom();
        return new ResponseData<>(
                HttpStatus.OK,
                "모든 채팅방 조회 성공",
                chatRoomList
        );
    }

    @GetMapping
    public String chatGET() {
        return "room";
    }
}
