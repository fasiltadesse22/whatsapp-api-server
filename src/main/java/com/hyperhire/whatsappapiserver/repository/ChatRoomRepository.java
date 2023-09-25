package com.hyperhire.whatsappapiserver.repository;

import com.hyperhire.whatsappapiserver.entity.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long> {
}
