package com.samin.jobserver.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.samin.jobsdk.SystemConstant;
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
        return new Queue(SystemConstant.JOB_QUEUE_NAME, false);
    }

    @Bean
    Queue callBackQueue() {
        return new Queue(SystemConstant.JOB_CALLBACK_QUEUE_NAME, false);
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
     * @return bean
     */
    @Bean
    Binding binding() {
        return BindingBuilder.bind(queue())
                .to(exchange())
                .with(SystemConstant.JOB_ROUTING_KEY);
    }

    @Bean
    Binding callBackBinding() {
        return BindingBuilder.bind(callBackQueue())
                .to(exchange())
                .with(SystemConstant.JOB_CALLBACK_ROUTING_KEY);
    }


    /**
     * 必须添加 Jackson 转换器，否则无法转换接收实体类
     *
     * @return 转换器
     */
    @Bean
    public Jackson2JsonMessageConverter converter() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        objectMapper.registerModule(new JavaTimeModule());
        return new Jackson2JsonMessageConverter(objectMapper);
    }
}
