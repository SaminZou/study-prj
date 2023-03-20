package com.samin.consumer.service;

import com.samin.common.SystemConstant;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 消费个数
 *
 * @author samin
 * @date 2022-12-25
 */
@Component
public class WorkerReceiver {

    @RabbitListener(queues = SystemConstant.workerQueueName)
    public void onMessage(String message) throws InterruptedException {
        System.out.println("线程名 " + Thread.currentThread().getName() + " : Message < " + message + " > ");
        Thread.sleep(3000L);
    }
}
