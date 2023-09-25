package com.hyperhire.whatsappapiserver.common.mapper;

import com.hyperhire.whatsappapiserver.common.auth.CurrentUser;
import com.hyperhire.whatsappapiserver.common.dto.BasicChatRoomInfo;
import com.hyperhire.whatsappapiserver.common.dto.BasicFileMessageInfo;
import com.hyperhire.whatsappapiserver.common.dto.BasicTextMessageInfo;
import com.hyperhire.whatsappapiserver.common.dto.BasicUserInfo;
import com.hyperhire.whatsappapiserver.common.dto.message.*;
import com.hyperhire.whatsappapiserver.common.dto.user.JoinOrLeaveChatRoomResponse;
import com.hyperhire.whatsappapiserver.entity.ChatRoom;
import com.hyperhire.whatsappapiserver.entity.ContentType;
import com.hyperhire.whatsappapiserver.entity.Message;
import com.hyperhire.whatsappapiserver.entity.WhatsappUser;
import com.hyperhire.whatsappapiserver.repository.ChatRoomRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.service.spi.ServiceException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class DataMapper {

    private final ChatRoomRepository chatRoomRepository;
    private final CurrentUser currentUser;

    private final ModelMapper modelMapper;

    public JoinOrLeaveChatRoomResponse userEntityToJoinChatRoomDto(WhatsappUser user, ChatRoom chatRoom) {

        BasicUserInfo basicUserInfo = new BasicUserInfo();
        modelMapper.map(user, basicUserInfo);

        JoinOrLeaveChatRoomResponse joinOrLeaveChatRoomResponse = new JoinOrLeaveChatRoomResponse();
        joinOrLeaveChatRoomResponse.setUser(basicUserInfo);

        joinOrLeaveChatRoomResponse.setChatRoom(modelMapper.map(chatRoom, BasicChatRoomInfo.class));

        return joinOrLeaveChatRoomResponse;
    }

    public Message SendTextMessageDtoToMessageEntity(SendTextMessageRequest sendTextMessageRequest) {

        ChatRoom chatRoom = chatRoomRepository.findById(sendTextMessageRequest.getChatRoomId())
                .orElseThrow(() -> new ServiceException("Chat room not found by id: " + sendTextMessageRequest.getChatRoomId()));

        Message message = new Message();
        message.setSender(currentUser.getUser());
        message.setChatRoom(chatRoom);
        message.setContentType(sendTextMessageRequest.getContentType());
        message.setText(sendTextMessageRequest.getText());

        return message;
    }

    public SendTextMessageResponse messageEntityToSendTextMessageDto(Message message) {

        SendTextMessageResponse sendTextMessageResponse = new SendTextMessageResponse();
        sendTextMessageResponse.setSender(modelMapper.map(message.getSender(), BasicUserInfo.class));
        sendTextMessageResponse.setChatRoom(modelMapper.map(message.getChatRoom(), BasicChatRoomInfo.class));
        sendTextMessageResponse.setMessage(modelMapper.map(message, BasicTextMessageInfo.class));

        return sendTextMessageResponse;
    }

    public Message SendFileMessageDtoToMessageEntity(Long chatRoomId, ContentType contentType, String filePath) {

        ChatRoom chatRoom = chatRoomRepository.findById(chatRoomId)
                .orElseThrow(() -> new ServiceException("Chat room not found by id: " + chatRoomId));

        Message message = new Message();
        message.setSender(currentUser.getUser());
        message.setChatRoom(chatRoom);
        message.setContentType(contentType);
        message.setFilePath(filePath);

        return message;
    }

    public SendFileMessageResponse messageEntityToSendFileMessageDto(Message message) {

        SendFileMessageResponse sendFileMessageResponse = new SendFileMessageResponse();
        sendFileMessageResponse.setSender(modelMapper.map(message.getSender(), BasicUserInfo.class));
        sendFileMessageResponse.setChatRoom(modelMapper.map(message.getChatRoom(), BasicChatRoomInfo.class));
        sendFileMessageResponse.setMessage(modelMapper.map(message, BasicFileMessageInfo.class));

        return sendFileMessageResponse;
    }

    public GetMessageByUserAndChatRoomResponse messageEntityToGetMessageDto(List<Message> messages, ChatRoom chatRoom) {

        GetMessageByUserAndChatRoomResponse getMessageByUserAndChatRoomResponse = new GetMessageByUserAndChatRoomResponse();
        getMessageByUserAndChatRoomResponse.setSender(modelMapper.map(currentUser.getUser(), BasicUserInfo.class));
        getMessageByUserAndChatRoomResponse.setChatRoom(modelMapper.map(chatRoom, BasicChatRoomInfo.class));

        List<MessageDto> messageDtos = messages.stream()
                .map(m -> modelMapper.map(m, MessageDto.class))
                .collect(Collectors.toList());

        getMessageByUserAndChatRoomResponse.setMessages(messageDtos);

        return getMessageByUserAndChatRoomResponse;
    }
}
