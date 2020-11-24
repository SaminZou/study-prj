package com.samin.designpattern.responsibility;

public class HandlerObj2 implements Handler {

    @Override
    public Boolean process(Request request) {
        if ("".equals(request.getContent())) {
            return false;
        } else {
            request.setContent(request.getContent() + " pass 2,");
            return true;
        }
    }
}
