package com.azazafizer.server_v1.api.friend.domain.repository;

import com.azazafizer.server_v1.api.friend.domain.entity.FriendRelation;
import com.azazafizer.server_v1.api.friend.domain.enums.FriendStatus;
import com.azazafizer.server_v1.api.member.domain.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FriendRelationRepository extends JpaRepository<FriendRelation, Integer> {

    List<FriendRelation> findByMyAndStatus(Member my, FriendStatus status);

    Optional<FriendRelation> findByMyAndFriend(Member my, Member friend);

    Optional<FriendRelation> findByFriendAndMy(Member friend, Member my);
}
