package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Message;
import com.example.demo.entity.UserAccount;
import com.example.demo.repository.MessageRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {
	
	 private final MessageRepository messageRepository;

	@Override
	public Message findMessageById(Integer messageId) {
		
		Optional<Message> optMessage = messageRepository.findByMessageId(messageId);
		Message msg;
		if (optMessage.isPresent()) {
			msg = optMessage.get();
			
			return msg;
		} else {
		    throw new IllegalArgumentException("メッセージが見つかりません: " + messageId);
		}
	}

	@Override
	public Message deleteMessage(Integer messageId) {
		 Message msg = findMessageById(messageId);
	        msg.setDeleteFlag(true);
	        return messageRepository.save(msg); 
	}

	@Override
	public List<Message> getReceivedMessages(UserAccount user) {
		return messageRepository.findReceivedByUser(user);
	}

	@Override
	public List<Message> getMessagesByUserId(Integer userId) {
		return messageRepository.findMessagesByUserId(userId);
	}

}
