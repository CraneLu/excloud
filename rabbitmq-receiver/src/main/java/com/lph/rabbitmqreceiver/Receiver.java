package com.lph.rabbitmqreceiver;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class Receiver {

    @RabbitListener(queues = "queue01")
    public void receiver01(String msg){
        System.out.println(msg);
    }

    @RabbitListener(queues = "topic_queue")
    public void topicReceiver(Object o){
        System.out.println(o);
    }

}
