package com.hyperhire.whatsappapiserver.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
public class ChatRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String name;

    @ManyToMany(mappedBy = "chatRooms")
    private Set<WhatsappUser> users = new HashSet<>();

    private LocalDateTime createdOn = LocalDateTime.now();

    @Override
    public boolean equals(Object o) {
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }
        return ((ChatRoom) o).id.equals(this.id);
    }
}
