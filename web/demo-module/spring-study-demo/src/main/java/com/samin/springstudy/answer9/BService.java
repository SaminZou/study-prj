package com.samin.springstudy.answer9;

import org.springframework.stereotype.Service;

@Service
public class BService {

    private final AService AService;

    public BService(AService AService) {
        this.AService = AService;
    }
}