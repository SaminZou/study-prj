package responsibility;

public class HandlerObj3 implements Handler {

    @Override
    public Boolean process(Request request) {
        if ("".equals(request.getContent())) {
            return false;
        } else {
            request.setContent(request.getContent() + " pass 3,");
            return true;
        }
    }
}
