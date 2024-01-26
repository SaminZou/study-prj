package com.samin.springstudy.answer3;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class Answer3Service implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(Answer3Service.class);
        Answer3Service service = (Answer3Service) applicationContext.getBean("answer3Service");
        service.transaction();
    }

    /**
     * 一般最好指定 rollbackFor，明确需要回滚的情况
     */
    @Transactional(rollbackFor = Exception.class)
    public void transaction() {
        System.out.println("执行事务");
    }
}
