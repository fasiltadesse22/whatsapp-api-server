package com.hyperhire.whatsappapiserver.service.user;

import com.hyperhire.whatsappapiserver.common.auth.CurrentUser;
import com.hyperhire.whatsappapiserver.common.dto.user.*;
import com.hyperhire.whatsappapiserver.common.mapper.DataMapper;
import com.hyperhire.whatsappapiserver.entity.ChatRoom;
import com.hyperhire.whatsappapiserver.entity.WhatsappUser;
import com.hyperhire.whatsappapiserver.repository.ChatRoomRepository;
import com.hyperhire.whatsappapiserver.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final ChatRoomRepository chatRoomRepository;
    private final DataMapper dataMapper;

    // Request scoped object
    private final CurrentUser currentUser;

    @Override
    public CreateUserResponse createUser(CreateUserRequest createUserRequest) {

        WhatsappUser whatsappUser = new WhatsappUser();
        modelMapper.map(createUserRequest, whatsappUser);

        whatsappUser = userRepository.save(whatsappUser);

        CreateUserResponse createUserResponse = new CreateUserResponse();
        modelMapper.map(whatsappUser, createUserResponse);

        return createUserResponse;
    }

    @Override
    public JoinOrLeaveChatRoomResponse joinChatRoom(JoinOrLeaveChatRoomRequest joinOrLeaveChatRoomRequest) {

        ChatRoom chatRoom = chatRoomRepository.findById(joinOrLeaveChatRoomRequest.getChatRoomId())
                .orElseThrow(() -> new IllegalArgumentException("Chat room doesn't exist by id: " + joinOrLeaveChatRoomRequest.getChatRoomId()));

        WhatsappUser user = currentUser.getUser();
        user.addUserTo(chatRoom);

        user = userRepository.save(user);

        return dataMapper.userEntityToJoinChatRoomDto(user, chatRoom);
    }

    @Override
    public JoinOrLeaveChatRoomResponse leaveChatRoom(JoinOrLeaveChatRoomRequest leaveChatRoomRequest) {

        ChatRoom chatRoom = chatRoomRepository.findById(leaveChatRoomRequest.getChatRoomId())
                .orElseThrow(() -> new IllegalArgumentException("Chat room doesn't exist by id: " + leaveChatRoomRequest.getChatRoomId()));

        // Check if the user is a member of the chat room
        currentUser.getUser().getChatRooms()
                .stream()
                .map(ChatRoom::getId)
                .filter(id -> id.equals(leaveChatRoomRequest.getChatRoomId()))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("User isn't member of the groupId: " + leaveChatRoomRequest.getChatRoomId()));


        WhatsappUser user = currentUser.getUser();
        user.removeFrom(chatRoom);

        user = userRepository.save(user);

        return dataMapper.userEntityToJoinChatRoomDto(user, chatRoom);
    }
}
