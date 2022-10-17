package structural.proxy.style1;

/**
 * 代理 1
 *
 * @author samin
 * @date 2022-10-17
 */
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
