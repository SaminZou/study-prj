package structural.adapter;

public class BirdAdapter implements Robot {

    Bird bird;

    public BirdAdapter(Bird bird) {
        this.bird = bird;
    }

    @Override
    public void cry() {
        bird.jiji();
    }

    @Override
    public void move() {
        bird.fly();
    }
}
