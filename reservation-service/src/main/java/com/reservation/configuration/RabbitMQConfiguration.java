package com.reservation.configuration;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.Binding.DestinationType;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfiguration {

	public static final String EXCHANGE_NAME = "reservation-exchange";
	public static final String QUEUE_NAME = "reservation-queue";

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

}
