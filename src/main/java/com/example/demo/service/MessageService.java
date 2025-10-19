package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Message;
import com.example.demo.entity.UserAccount;

public interface MessageService {

	Message findMessageById(Integer messageId);

	Message deleteMessage(Integer messageId);

	List<Message> getReceivedMessages(UserAccount user);
}
