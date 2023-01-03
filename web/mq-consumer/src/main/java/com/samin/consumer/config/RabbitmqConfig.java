package com.samin.consumer.config;

import com.samin.consumer.service.Receiver;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitmqConfig {

    public static final String topicExchangeName = "samin-dev-exchange";

    public static final String queueName = "samin-dev-queue";

    /**
     * 新建队列
     *
     * @return bean
     */
    @Bean
    Queue queue() {
        return new Queue(queueName, false);
    }

    /**
     * 新建交换机
     *
     * @return bean
     */
    @Bean
    TopicExchange exchange() {
        return new TopicExchange(topicExchangeName);
    }

    /**
     * 绑定队列和交换机
     *
     * @param queue    队列
     * @param exchange 交换机
     * @return bean
     */
    @Bean
    Binding binding(Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with("foo.bar.#");
    }

    /**
     * 监听队列
     *
     * @param connectionFactory 连接配置
     * @param listenerAdapter   监听适配器
     * @return
     */
    @Bean
    SimpleMessageListenerContainer container(ConnectionFactory connectionFactory, MessageListenerAdapter listenerAdapter) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames(queueName);
        container.setMessageListener(listenerAdapter);
        return container;
    }

    /**
     * 消息监听适配器
     *
     * @param receiver 监听对象
     * @return bean
     */
    @Bean
    MessageListenerAdapter listenerAdapter(Receiver receiver) {
        return new MessageListenerAdapter(receiver, "receiveMessage");
    }
}
