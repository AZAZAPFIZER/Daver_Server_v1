package com.azazafizer.server_v1.api.friend.domain.repository;

import com.azazafizer.server_v1.api.friend.domain.entity.Friend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FriendRepository extends JpaRepository<Friend, Integer> {
}
