package behavioural.responsibility.basehandler;

import behavioural.responsibility.Request;
import behavioural.responsibility.handler.Handler;
import java.util.ArrayList;
import java.util.List;

public class HandlerChain implements Handler {

    private final List<Handler> handlers = new ArrayList<>();

    public void addHandler(Handler handler) {
        this.handlers.add(handler);
    }

    @Override
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
