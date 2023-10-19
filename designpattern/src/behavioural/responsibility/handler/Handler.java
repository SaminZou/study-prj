package behavioural.responsibility.handler;

import behavioural.responsibility.Request;

public interface Handler {

    boolean process(Request request);
}
