package com.book.configuration;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.Binding.DestinationType;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.book.listeners.MessageListener;

@Configuration
public class RabbitMQConfiguration {

	private static final String EXCHANGE_NAME = "reservation-exchange";
	private static final String QUEUE_NAME = "reservation-queue";
	private static final String LISTENER_METHOD = "receiveMessage";

	@Bean
	public Queue queue() {
		return QueueBuilder.durable(QUEUE_NAME).build();
	}

	@Bean
	public Exchange exchange() {
		return ExchangeBuilder.directExchange(EXCHANGE_NAME).build();
	}

	@Bean
	public Binding binding() {
		return new Binding(QUEUE_NAME, DestinationType.QUEUE, EXCHANGE_NAME, QUEUE_NAME, null);
	}

	@Bean
	SimpleMessageListenerContainer container(ConnectionFactory connectionFactory,
			MessageListenerAdapter listenerAdapter) {
		SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
		container.setConnectionFactory(connectionFactory);
		container.setQueueNames(QUEUE_NAME);
		container.setMessageListener(listenerAdapter);
		return container;
	}

	@Bean
	MessageListenerAdapter listenerAdapter(MessageListener listener) {
		return new MessageListenerAdapter(listener, LISTENER_METHOD);
	}

}
