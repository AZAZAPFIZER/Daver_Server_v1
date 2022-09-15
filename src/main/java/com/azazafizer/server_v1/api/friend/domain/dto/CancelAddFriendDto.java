package com.azazafizer.server_v1.api.friend.domain.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
public class CancelAddFriendDto {

    @NotNull
    private int memberId;
}
