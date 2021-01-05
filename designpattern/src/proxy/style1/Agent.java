package proxy.style1;

public class Agent implements Subject {

    private final Subject star;

    public Agent(Subject star) {
        this.star = star;
    }

    @Override
    public void movie() {
        System.out.println("I am agent. I agree this work...");
        star.movie();
    }
}
