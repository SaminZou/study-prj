package structural.proxy.style2;

/**
 * 明星 2
 *
 * @author samin
 * @date 2022-10-17
 */
public class Star2 implements Subject2 {

    @Override
    public void movie() {
        System.out.println("I am a star. start working...");
    }

    @Override
    public Agent2 getAgent() {
        return new Agent2(this);
    }
}
