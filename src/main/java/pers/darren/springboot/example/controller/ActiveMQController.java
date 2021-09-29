package pers.darren.springboot.example.controller;

import javax.jms.BytesMessage;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.Queue;
import javax.jms.StreamMessage;
import javax.jms.TextMessage;
import javax.jms.Topic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.annotation.JmsListeners;
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

    private static final Logger logger = LoggerFactory.getLogger(ActiveMQController.class);
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

    /**
     * <pre>
     * ActiveMQ点对点(P2P)模型，队列模式
     * spring.jms.pub-sub-domain: false
     * </pre>
     *
     * @CreatedBy Darren Luo
     * @CreatedTime Sep 27, 2021 5:50:09 PM
     * @param message
     */
    @GetMapping("/sendQueueMessage")
    public void sendQueueMessage(final String message) {
        this.jmsTemplate.convertAndSend("test-queue", message);
    }

    /**
     * <pre>
     * ActiveMQ发布/订阅(Pub/Sub)模型，主题模式
     * spring.jms.pub-sub-domain: true
     * </pre>
     *
     * @CreatedBy Darren Luo
     * @CreatedTime Sep 27, 2021 5:50:12 PM
     * @param message
     */
    @GetMapping("/sendTopicMessage")
    public void sendTopicMessage(final String message) {
        this.jmsTemplate.convertAndSend("test-topic", message);
    }

    /**
     * <pre>
     * 生产消息至ActiveMQ时，若明确目的地是队列或者主题
     * 则将无视spring.jms.pub-sub-domain属性配置
     * </pre>
     *
     * @CreatedBy Darren Luo
     * @CreatedTime Sep 29, 2021 3:25:18 PM
     */
    @GetMapping("/sendQueueTopicMessage")
    public void sendQueueTopicMessage() {
        this.jmsTemplate.convertAndSend(this.smsQueue, "This is a sms queue message!");
        this.jmsTemplate.convertAndSend(this.mailQueue, "This is a mail queue message!");
        this.jmsTemplate.convertAndSend(this.smsTopic, "This is a sms topic message!");
        this.jmsTemplate.convertAndSend(this.mailTopic, "This is a mail topic message!");
    }

    /**
     * <pre>
     * ActiveMQ点对点(P2P)模型，队列模式
     * spring.jms.pub-sub-domain: false
     * </pre>
     *
     * @CreatedBy Darren Luo
     * @CreatedTime Sep 27, 2021 5:51:56 PM
     * @param message
     */
    @JmsListener(destination = "test-queue")
    public void receiveQueueMessage(final Message message) {
        try {
            if (message instanceof final TextMessage textMessage) {
                logger.info("Received queue text message>>>" + textMessage.getText());
            } else if (message instanceof final MapMessage mapMessage) {
                logger.info("Received queue map message>>>" + mapMessage.getString("name"));
            } else if (message instanceof final BytesMessage bytesMessage) {
                logger.info("Received queue bytes message>>>" + bytesMessage);
            } else if (message instanceof final StreamMessage streamMessage) {
                logger.info("Received queue stream message>>>" + streamMessage.readString());
            } else {
                logger.info("Received queue message>>>" + message);
            }
        } catch (final JMSException e) {
            logger.error(e.getMessage(), e);
        }
    }

    /**
     * <pre>
     * ActiveMQ发布/订阅(Pub/Sub)模型，主题模式
     * spring.jms.pub-sub-domain: true
     * </pre>
     *
     * @CreatedBy Darren Luo
     * @CreatedTime Sep 27, 2021 5:59:36 PM
     * @param message
     */
    @JmsListener(destination = "test-topic")
    public void receiveTopicMessage(final Message message) {
        try {
            if (message instanceof final TextMessage textMessage) {
                logger.info("Received topic text message>>>" + textMessage.getText());
            } else if (message instanceof final MapMessage mapMessage) {
                logger.info("Received topic map message>>>" + mapMessage.getString("name"));
            } else if (message instanceof final BytesMessage bytesMessage) {
                logger.info("Received topic bytes message>>>" + bytesMessage);
            } else if (message instanceof final StreamMessage streamMessage) {
                logger.info("Received topic stream message>>>" + streamMessage.readString());
            } else {
                logger.info("Received topic message>>>" + message);
            }
        } catch (final JMSException e) {
            logger.error(e.getMessage(), e);
        }
    }

    /**
     * <pre>
     * 从ActiveMQ消费消息时，若明确指定了ContainerFactory
     * 则将无视spring.jms.pub-sub-domain属性配置
     * </pre>
     *
     * @CreatedBy Darren Luo
     * @CreatedTime Sep 29, 2021 3:28:06 PM
     * @param message
     */
    @JmsListeners({
        @JmsListener(destination = "${spring.activemq.queue-names.smsQueue}", containerFactory = "queueListenerContainerFactory"),
        @JmsListener(destination = "${spring.activemq.queue-names.mailQueue}", containerFactory = "queueListenerContainerFactory"),
        @JmsListener(destination = "${spring.activemq.topic-names.smsTopic}", containerFactory = "topicListenerContainerFactory"),
        @JmsListener(destination = "${spring.activemq.topic-names.mailTopic}", containerFactory = "topicListenerContainerFactory") })
    public void receiveQueueTopicMessageWithContainerFactory(final Message message) {
        try {
            if (message instanceof final TextMessage textMessage) {
                logger.info("Received text message>>>" + textMessage.getText());
            } else if (message instanceof final MapMessage mapMessage) {
                logger.info("Received map message>>>" + mapMessage.getString("name"));
            } else if (message instanceof final BytesMessage bytesMessage) {
                logger.info("Received bytes message>>>" + bytesMessage);
            } else if (message instanceof final StreamMessage streamMessage) {
                logger.info("Received stream message>>>" + streamMessage.readString());
            } else {
                logger.info("Received message>>>" + message);
            }
        } catch (final JMSException e) {
            logger.error(e.getMessage(), e);
        }
    }
}