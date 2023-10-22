package behavioural.memento.originator;

import behavioural.memento.memento.ConcreteMemento;

public class Player implements Originator {

    private int bloodFlow;
    private int magicPoint;

    public Player(int bloodFlow, int magicPoint) {
        this.bloodFlow = bloodFlow;
        this.magicPoint = magicPoint;
    }

    public int getBloodFlow() {
        return bloodFlow;
    }

    public void setBloodFlow(int bloodFlow) {
        this.bloodFlow = bloodFlow;
    }

    public int getMagicPoint() {
        return magicPoint;
    }

    public void setMagicPoint(int magicPoint) {
        this.magicPoint = magicPoint;
    }

    /**
     * 展示角色当前状态
     */
    public void display() {
        System.out.println("用户当前状态:" + " 血量:" + getBloodFlow() + ";蓝量:" + getMagicPoint());
    }

    /**
     * 生成快照
     */
    public ConcreteMemento createSnapshot() {
        Player player = new Player(this.getBloodFlow(), this.magicPoint);
        return new ConcreteMemento(player);
    }
}
