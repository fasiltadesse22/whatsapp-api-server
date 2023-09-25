package com.hyperhire.whatsappapiserver.repository;

import com.hyperhire.whatsappapiserver.entity.WhatsappUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<WhatsappUser, Long> {
    Optional<WhatsappUser> findByUsernameAndPassword(String userName, String password);
}
