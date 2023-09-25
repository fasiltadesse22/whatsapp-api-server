package com.hyperhire.whatsappapiserver.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class WhatsappUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;

    private String lastName;

    @Column(unique = true)
    private String username;

    private String password;

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(
            name = "user_chatroom",
            joinColumns = { @JoinColumn(name = "user_id") },
            inverseJoinColumns = { @JoinColumn(name = "chatroom_id") }
    )
    private List<ChatRoom> chatRooms;

    public void addUserTo(ChatRoom chatRoom) {
        if (this.chatRooms.contains(chatRoom))
            throw new RuntimeException("User already joined the chat room");

        this.chatRooms.add(chatRoom);
    }

    public void removeFrom(ChatRoom chatRoom) {
        this.chatRooms.remove(chatRoom);
    }
}
