package com.samin.producer.config;

import com.samin.common.SystemConstant;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitmqConfig {

    /**
     * 新建队列
     *
     * @return bean
     */
    @Bean
    Queue queue() {
        return new Queue(SystemConstant.QUEUE_NAME, false);
    }

    /**
     * 新建交换机
     *
     * @return bean
     */
    @Bean
    TopicExchange exchange() {
        return new TopicExchange(SystemConstant.TOPIC_EXCHANGE_NAME);
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
        return BindingBuilder.bind(queue)
                .to(exchange)
                .with("foo.bar.#");
    }

    /**
     * 追加的消息队列
     *
     * @return bean
     */
    @Bean
    Queue msgQueue() {
        return new Queue(SystemConstant.MSG_CONSUMER_QUEUE_NAME, false);
    }

    /**
     * 追加的消息队列绑定
     *
     * @param exchange 交换机
     * @return bean
     */
    @Bean
    Binding msgBinding(TopicExchange exchange) {
        return BindingBuilder.bind(msgQueue())
                .to(exchange)
                .with("msg.#");
    }

    /**
     * 必须添加 Jackson 转换器，否则无法转换接收实体类
     *
     * @return 转换器
     */
    @Bean
    public Jackson2JsonMessageConverter converter() {
        return new Jackson2JsonMessageConverter();
    }

    /**
     * 追加的消息队列
     *
     * @return bean
     */
    @Bean
    Queue workerQueue() {
        return new Queue(SystemConstant.WORKER_QUEUE_NAME, false);
    }

    /**
     * 追加的消息队列绑定
     *
     * @param exchange 交换机
     * @return bean
     */
    @Bean
    Binding workerBinding(TopicExchange exchange) {
        return BindingBuilder.bind(workerQueue())
                .to(exchange)
                .with("work.#");
    }
}
