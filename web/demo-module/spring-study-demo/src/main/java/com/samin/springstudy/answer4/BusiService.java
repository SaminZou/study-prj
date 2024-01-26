package com.samin.springstudy.answer4;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BusiService implements CommandLineRunner {

    @Autowired
    private Answer4Service answer4Service;

    @Override
    public void run(String... args) throws Exception {
        answer4Service.main();
    }
}
