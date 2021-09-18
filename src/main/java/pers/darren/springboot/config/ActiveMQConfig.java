package pers.darren.springboot.config;

import javax.jms.ConnectionFactory;
import javax.jms.Queue;
import javax.jms.Topic;

import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;

/**
 * ActiveMQ配置类
 *
 * @CreatedBy Darren Luo
 * @CreatedTime Sep 15, 2021 7:52:31 PM
 */
@Configuration
public class ActiveMQConfig {
    /**
     * 短信Queue名称
     */
    @Value("${spring.activemq.queue-names.smsQueue}")
    private String smsQueueName;
    /**
     * 邮件Queue名称
     */
    @Value("${spring.activemq.queue-names.mailQueue}")
    private String mailQueueName;
    /**
     * 短信Topic名称
     */
    @Value("${spring.activemq.topic-names.smsTopic}")
    private String smsTopicName;
    /**
     * 邮件Topic名称
     */
    @Value("${spring.activemq.topic-names.mailTopic}")
    private String mailTopicName;

    /**
     * 短信Queue
     *
     * @CreatedBy Darren Luo
     * @CreatedTime Sep 15, 2021 8:45:20 PM
     * @return
     */
    @Bean
    public Queue smsQueue() {
        return new ActiveMQQueue(this.smsQueueName);
    }

    /**
     * 邮件Queue
     *
     * @CreatedBy Darren Luo
     * @CreatedTime Sep 15, 2021 8:45:30 PM
     * @return
     */
    @Bean
    public Queue mailQueue() {
        return new ActiveMQQueue(this.mailQueueName);
    }

    /**
     * 短信Topic
     *
     * @CreatedBy Darren Luo
     * @CreatedTime Sep 15, 2021 8:48:13 PM
     * @return
     */
    @Bean
    public Topic smsTopic() {
        return new ActiveMQTopic(this.smsTopicName);
    }

    /**
     * 邮件Topic
     *
     * @CreatedBy Darren Luo
     * @CreatedTime Sep 15, 2021 8:48:38 PM
     * @return
     */
    @Bean
    public Topic mailTopic() {
        return new ActiveMQTopic(this.mailTopicName);
    }

    /**
     * Queue模式的消息监听器
     *
     * @CreatedBy Darren Luo
     * @CreatedTime Sep 15, 2021 8:11:20 PM
     * @param connectionFactory
     * @return
     */
    @Bean
    public JmsListenerContainerFactory<?> queueListenerContainerFactory(final ConnectionFactory connectionFactory) {
        final var defaultJmsListenerContainerFactory = new DefaultJmsListenerContainerFactory();
        defaultJmsListenerContainerFactory.setConnectionFactory(connectionFactory);
        defaultJmsListenerContainerFactory.setPubSubDomain(false);
        return defaultJmsListenerContainerFactory;
    }

    /**
     * Topic模式的消息监听器
     *
     * @CreatedBy Darren Luo
     * @CreatedTime Sep 15, 2021 8:12:28 PM
     * @param connectionFactory
     * @return
     */
    @Bean
    public JmsListenerContainerFactory<?> topicListenerContainerFactory(final ConnectionFactory connectionFactory) {
        final var defaultJmsListenerContainerFactory = new DefaultJmsListenerContainerFactory();
        defaultJmsListenerContainerFactory.setConnectionFactory(connectionFactory);
        defaultJmsListenerContainerFactory.setPubSubDomain(true);
        return defaultJmsListenerContainerFactory;
    }
}