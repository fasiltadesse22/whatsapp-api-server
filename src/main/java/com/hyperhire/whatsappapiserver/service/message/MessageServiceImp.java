package com.hyperhire.whatsappapiserver.service.message;

import com.hyperhire.whatsappapiserver.common.auth.CurrentUser;
import com.hyperhire.whatsappapiserver.common.dto.message.GetMessageByUserAndChatRoomResponse;
import com.hyperhire.whatsappapiserver.common.dto.message.SendFileMessageResponse;
import com.hyperhire.whatsappapiserver.common.dto.message.SendTextMessageRequest;
import com.hyperhire.whatsappapiserver.common.dto.message.SendTextMessageResponse;
import com.hyperhire.whatsappapiserver.common.mapper.DataMapper;
import com.hyperhire.whatsappapiserver.common.util.FileHelper;
import com.hyperhire.whatsappapiserver.entity.ChatRoom;
import com.hyperhire.whatsappapiserver.entity.ContentType;
import com.hyperhire.whatsappapiserver.entity.Message;
import com.hyperhire.whatsappapiserver.repository.ChatRoomRepository;
import com.hyperhire.whatsappapiserver.repository.MessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MessageServiceImp implements MessageService {

    private final MessageRepository messageRepository;
    private final DataMapper dataMapper;
    private final FileHelper fileHelper;
    private final ChatRoomRepository chatRoomRepository;

    private final CurrentUser currentUser;

    @Override
    public SendTextMessageResponse sendTextMessage(SendTextMessageRequest sendTextMessageRequest) {

        currentUser.getUser().getChatRooms()
                .stream()
                .map(ChatRoom::getId)
                .filter(id -> id.equals(sendTextMessageRequest.getChatRoomId()))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("User isn't member of the chat room: " + sendTextMessageRequest.getChatRoomId()));

        Message message = dataMapper.SendTextMessageDtoToMessageEntity(sendTextMessageRequest);
        message = messageRepository.save(message);

        return dataMapper.messageEntityToSendTextMessageDto(message);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public SendFileMessageResponse sendFileMessage(MultipartFile file, ContentType contentType, Long chatRoomId) {

        currentUser.getUser().getChatRooms()
                .stream()
                .map(ChatRoom::getId)
                .filter(id -> id.equals(chatRoomId))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("User isn't member of the chat room: " + chatRoomId));

        Path path = Paths.get(contentType.getRootPath()).resolve(file.getOriginalFilename());

        Message message = dataMapper.SendFileMessageDtoToMessageEntity(chatRoomId, contentType, path.toAbsolutePath().toString());
        message = messageRepository.save(message);

        fileHelper.upload(file, path);

        return dataMapper.messageEntityToSendFileMessageDto(message);
    }

    @Override
    public GetMessageByUserAndChatRoomResponse getMessageByUserAndChatRoom(Long chatRoomId) {

        currentUser.getUser().getChatRooms()
                .stream()
                .map(ChatRoom::getId)
                .filter(id -> id.equals(chatRoomId))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("User isn't member of the chat room: " + chatRoomId));


        List<Message> messages = messageRepository.findAllBySender_IdAndChatRoom_IdOrderByCreatedOnDesc(currentUser.getUser().getId(), chatRoomId);

        return dataMapper.messageEntityToGetMessageDto(messages, chatRoomRepository.findById(chatRoomId).get());
    }
}
