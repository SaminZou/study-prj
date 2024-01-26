package com.samin.springstudy.answer2;

import org.springframework.beans.factory.InitializingBean;

public class Answer2Service  implements InitializingBean {

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("校验操作等");
        System.out.println("answer2Service 已经实例化完成");
    }
}
