package com.azazafizer.server_v1.api.member.domain.ro;

import com.azazafizer.server_v1.api.member.domain.entity.Member;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@Getter
@AllArgsConstructor
public class MemberRo {

    private List<MemberInfo> memberInfo;
    private long count;

    @Getter
    public static class MemberInfo {
        private final String id;
        private final String name;
        private final String address;
        private final String x;
        private final String y;
        @JsonFormat(pattern = "yyyy-MM-dd")
        private final LocalDate joinedAt;

        public MemberInfo(Member member) {
            this.id = member.getId();
            this.name = member.getName();
            this.address = member.getAddress();
            this.x = member.getX();
            this.y = member.getY();
            this.joinedAt = member.getJoinedAt();
        }

    }
}
