package com.hyperhire.whatsappapiserver.service.chatroom;

import com.hyperhire.whatsappapiserver.common.auth.CurrentUser;
import com.hyperhire.whatsappapiserver.common.dto.BasicChatRoomInfo;
import com.hyperhire.whatsappapiserver.common.dto.chatroom.ChatRoomRequest;
import com.hyperhire.whatsappapiserver.common.dto.chatroom.ChatRoomResponse;
import com.hyperhire.whatsappapiserver.common.dto.chatroom.GetAllChatRoomsRequest;
import com.hyperhire.whatsappapiserver.common.dto.chatroom.ChatRoomListResponse;
import com.hyperhire.whatsappapiserver.entity.ChatRoom;
import com.hyperhire.whatsappapiserver.repository.ChatRoomRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ChatRoomServiceImpl implements ChatRoomService {

    private final ModelMapper modelMapper;
    private final ChatRoomRepository chatRoomRepository;

    private final CurrentUser currentUser;

    @Override
    public ChatRoomResponse createChatRoom(ChatRoomRequest createChatRoomRequest) {

        ChatRoom chatRoom = new ChatRoom();
        modelMapper.map(createChatRoomRequest, chatRoom);

        chatRoom = chatRoomRepository.save(chatRoom);

        ChatRoomResponse chatRoomResponse = new ChatRoomResponse();
        modelMapper.map(chatRoom, chatRoomResponse);

        return chatRoomResponse;
    }

    @Override
    public ChatRoomListResponse getAllChatRooms(GetAllChatRoomsRequest getAllChatRoomsRequest) {

        List<ChatRoom> chatRooms = chatRoomRepository.findAll();

        List<BasicChatRoomInfo> chatRoomResponses = chatRooms
                .stream()
                .map(cr -> modelMapper.map(cr, BasicChatRoomInfo.class))
                .collect(Collectors.toList());

        ChatRoomListResponse chatRoomListResponse = new ChatRoomListResponse();
        chatRoomListResponse.setChatRooms(chatRoomResponses);

        return chatRoomListResponse;
    }

    @Override
    public ChatRoomListResponse getCurrentUserChatRooms() {

        List<BasicChatRoomInfo> chatRoomResponses = currentUser.getUser().getChatRooms().stream()
                .map(cr -> modelMapper.map(cr, BasicChatRoomInfo.class))
                .collect(Collectors.toList());

        ChatRoomListResponse chatRoomListResponse = new ChatRoomListResponse();
        chatRoomListResponse.setChatRooms(chatRoomResponses);

        return chatRoomListResponse;
    }

}
