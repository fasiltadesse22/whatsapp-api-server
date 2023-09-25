package com.hyperhire.whatsappapiserver.repository;

import com.hyperhire.whatsappapiserver.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {

    List<Message> findAllBySender_IdAndChatRoom_IdOrderByCreatedOnDesc(Long senderId, Long chatRoomId);
}
