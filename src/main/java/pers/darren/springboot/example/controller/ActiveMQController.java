package pers.darren.springboot.example.controller;

import javax.jms.Queue;
import javax.jms.Topic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ActiveMQ操作案例
 *
 * @CreatedBy Darren Luo
 * @CreatedTime Sep 15, 2021 4:36:08 PM
 */
@RestController
@RequestMapping("/activemq")
public class ActiveMQController {

    @Autowired
    private Queue smsQueue;
    @Autowired
    private Queue mailQueue;
    @Autowired
    private Topic smsTopic;
    @Autowired
    private Topic mailTopic;
    @Autowired
    private JmsTemplate jmsTemplate;

    @GetMapping("/sendQueueMessage")
    public void sendQueueMessage(final String message) {
        this.jmsTemplate.convertAndSend("test-queue", message);
    }

    @GetMapping("/sendTopicMessage")
    public void sendTopicMessage(final String message) {
        this.jmsTemplate.convertAndSend("test-topic", message);
    }

    @GetMapping("/sendQueueTopicMessage")
    public void sendQueueTopicMessage() {
        this.jmsTemplate.convertAndSend(this.smsQueue, "This is a sms queue message!");
        this.jmsTemplate.convertAndSend(this.mailQueue, "This is a mail queue message!");
        this.jmsTemplate.convertAndSend(this.smsTopic, "This is a sms topic message!");
        this.jmsTemplate.convertAndSend(this.mailTopic, "This is a mail topic message!");
    }
}