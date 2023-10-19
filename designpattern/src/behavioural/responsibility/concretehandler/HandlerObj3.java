package behavioural.responsibility.concretehandler;

import behavioural.responsibility.Request;
import behavioural.responsibility.handler.Handler;

public class HandlerObj3 implements Handler {

    @Override
    public boolean process(Request request) {
        if ("".equals(request.getContent())) {
            return false;
        } else {
            request.setContent(request.getContent() + " pass 3,");
            return true;
        }
    }
}
