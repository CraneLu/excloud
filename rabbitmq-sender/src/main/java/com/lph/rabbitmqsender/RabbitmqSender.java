package com.lph.rabbitmqsender;

import com.lph.rabbitmqsender.config.RabbitmqSenderConfig;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RabbitmqSender {

    @Autowired private AmqpTemplate amqpTemplate;

    public void send(String queue, Object o){
        amqpTemplate.convertAndSend(queue, o);
    }

    //topic
    public void sendTopic(String routingKey, Object object){
        amqpTemplate.convertAndSend(RabbitmqSenderConfig.TOPIC_EXCHANGE_NAME,
                routingKey,
                object);
    }

}
