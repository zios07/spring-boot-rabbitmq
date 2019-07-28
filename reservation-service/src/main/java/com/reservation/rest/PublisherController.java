package com.reservation.rest;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.reservation.configuration.RabbitMQConfiguration;

@RestController
public class PublisherController {

	@Autowired
	private RabbitTemplate rabbitTemplate;
	
	@GetMapping
	public String sendMessage() {
		String message = "this is a random message - " + System.currentTimeMillis();
		rabbitTemplate.convertAndSend(RabbitMQConfiguration.QUEUE_NAME, message);
		return message;
	}
	
}
