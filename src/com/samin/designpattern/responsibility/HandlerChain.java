package com.samin.designpattern.responsibility;

import java.util.ArrayList;
import java.util.List;

public class HandlerChain {

    private final List<Handler> handlers = new ArrayList<>();

    public void addHandler(Handler handler) {
        this.handlers.add(handler);
    }

    public boolean process(Request request) {
        for (Handler ele : handlers) {
            if (!ele.process(request)) {
                System.out.println("error process");
                return false;
            }
        }

        System.out.println("run success, the result is : " + request.getContent());
        return true;
    }
}
