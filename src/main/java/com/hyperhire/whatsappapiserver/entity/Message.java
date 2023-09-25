package com.hyperhire.whatsappapiserver.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private ContentType contentType;

    @ManyToOne
    private WhatsappUser sender;

    @ManyToOne
    private ChatRoom chatRoom;

    private String text;

    private String filePath;

    private LocalDateTime createdOn = LocalDateTime.now();
}