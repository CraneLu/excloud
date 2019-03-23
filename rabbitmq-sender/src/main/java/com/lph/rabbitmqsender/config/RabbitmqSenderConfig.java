package com.lph.rabbitmqsender.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitmqSenderConfig {

    //Direct
    @Bean
    public Queue queue(){
        return new Queue("queue01");
    }


    //Topic
    public static final String TOPIC_EXCHANGE_NAME = "topic_exchange";
    public static final String TOPIC_QUEUE_NAME = "topic_queue";
    public static final String TOPIC_ROUTING_KEY = "topic.routing.key.*";
    @Bean
    public TopicExchange topicExchange(){
        return new TopicExchange(TOPIC_EXCHANGE_NAME);
    }
    @Bean
    public Queue topicQueue(){
        return new Queue(TOPIC_QUEUE_NAME);
    }
    @Bean
    public Binding topicBinding(){
        return BindingBuilder.bind(topicQueue()).to(topicExchange()).with(TOPIC_ROUTING_KEY);
    }

}
