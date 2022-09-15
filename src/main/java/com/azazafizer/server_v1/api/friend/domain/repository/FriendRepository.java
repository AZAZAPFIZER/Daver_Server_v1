package com.azazafizer.server_v1.api.friend.domain.repository;

import com.azazafizer.server_v1.api.friend.domain.entity.Friend;
import com.azazafizer.server_v1.api.friend.domain.enums.FriendStatus;
import com.azazafizer.server_v1.api.member.domain.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FriendRepository extends JpaRepository<Friend, Integer> {

    List<Friend> findByMyAndStatus(Member my, FriendStatus status);

    Optional<Friend> findByMyAndFriend(Member my, Member friend);
}
