package com.book.listeners;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.book.model.Message;
import com.book.repository.MessageRepository;

@Component
public class MessageListener {

	protected Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private MessageRepository repository;

	public void receiveMessage(String message) {
		logger.info("Received (String) " + message);
		processMessage(message);
	}

	public void receiveMessage(byte[] message) {
		String strMessage = new String(message);
		logger.info("Received (No String) " + strMessage);
		processMessage(strMessage);
	}

	private void processMessage(String content) {
		Message message = new Message();
		message.setContent(content);
		message = repository.save(message);
		logger.info("Message saved to database with id : " + message.getMessageId());
	}

}
