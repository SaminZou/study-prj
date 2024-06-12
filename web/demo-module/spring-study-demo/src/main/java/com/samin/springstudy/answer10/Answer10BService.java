package com.samin.springstudy.answer10;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Answer10BService {

    @Autowired
    private Answer10AService answer10AService;
}