package structural.adapter.adapter;

import structural.adapter.Wolf;

/**
 * 驯服狼的适配器（模仿狗的行为动作）
 * <p>
 * Description: 让狼也能随时按照下达指令来行动
 * <p>
 * Created By: Samin
 * <p>
 * Created Date: 2023-10-16
 */
public class TameWolfAdapter implements Tame {

    Wolf wolf;

    /**
     * 取得要适配的对象的引用
     *
     * @param wolf
     */
    public TameWolfAdapter(Wolf wolf) {
        this.wolf = wolf;
    }

    /**
     * 实现接口中的方法，只需要在相应的方法间进行转换即可完成
     */
    @Override
    public void wang() {
        System.out.println("驯服狼叫...");
        wolf.wangIfItWant();
    }

    @Override
    public void run() {
        System.out.println("驯服狼跑...");
        wolf.runIfItWant();
    }
}
