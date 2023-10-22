package behavioural.memento;

import behavioural.memento.caretaker.Caretaker;
import behavioural.memento.memento.ConcreteMemento;
import behavioural.memento.originator.Player;

/**
 * 备忘录模式
 *
 * @author samin
 * @date 2021-01-05
 */
public class Client {

    public static void main(String[] args) {
        // 声明责任人
        Caretaker playerMemento = new Caretaker();

        // 打 BOSS 之前：血、蓝全部满值
        Player player = new Player(100, 100);
        System.out.println("----------大战BOSS之前----------");
        player.display();

        // 存档
        playerMemento.save(player);

        // 大战 BOSS
        player.setBloodFlow(80);
        player.setMagicPoint(80);
        System.out.println("----------大战BOSS 第一波----------");
        player.display();

        // 存档
        playerMemento.save(player);

        // 大战 BOSS，快 Game Over了
        player.setBloodFlow(20);
        player.setMagicPoint(20);
        System.out.println("----------大战BOSS 第二波----------");
        player.display();

        System.out.println("-----恢复存档后------");
        // 恢复存档
        ConcreteMemento memento = playerMemento.undo();
        player = memento.restore();
        player.display();

        System.out.println("-----再次恢复存档后------");
        // 恢复存档
        memento = playerMemento.undo();
        player = memento.restore();
        player.display();
    }
}
