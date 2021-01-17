package com.example.rmqc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/consumer")
public class Consumer {

    String message;
    Logger logger = LoggerFactory.getLogger(Consumer.class);

    @RabbitListener(queues = "otherQueue")
    public void worker1(String message) {
        if (!"".equals(message)) {
            this.message = message;
            logger.info(message);
        }
    }

    @GetMapping("/get")
    public String receiveMessage() {
        return message;
    }
}
