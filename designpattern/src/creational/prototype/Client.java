package creational.prototype;

/**
 * 原型模式
 *
 * @author samin
 * @date 2021-01-05
 */
public class Client {

    public static void main(String[] args) {
        System.out.println("——普通用法");
        Area area = new Area();
        area.setUnit("RMB");

        // 原型实例，100RMB的钞票
        Money money = new Money(100, area);

        for (int i = 1; i <= 3; i++) {
            try {
                Money cloneMoney = money.clone();
                cloneMoney.setFaceValue(i * 100);
                System.out.println(
                        "这张是" + cloneMoney.getFaceValue() + cloneMoney.getArea().getUnit() + "的钞票");
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(
                "==============================================================================");

        // 浅拷贝
        System.out.println("——浅拷贝");
        try {
            Money cloneMoney = money.clone();
            cloneMoney.setFaceValue(200);
            area.setUnit("美元");

            System.out.println("原型实例的面值：" + money.getFaceValue() + money.getArea().getUnit());
            System.out.println(
                    "拷贝实例的面值：" + cloneMoney.getFaceValue() + cloneMoney.getArea().getUnit());
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        System.out.println(
                "==============================================================================");

        // 深拷贝
        System.out.println("——深拷贝");
        AreaDeep areaDeep = new AreaDeep();
        areaDeep.setUnit("RMB");

        MoneyDeep moneyDeep = new MoneyDeep(100, areaDeep);

        try {
            MoneyDeep cloneMoney = moneyDeep.clone();
            cloneMoney.setFaceValue(200);
            areaDeep.setUnit("美元");

            System.out.println(
                    "原型实例的面值：" + moneyDeep.getFaceValue() + moneyDeep.getAreaDeep().getUnit());
            System.out.println(
                    "拷贝实例的面值：" + cloneMoney.getFaceValue() + cloneMoney.getAreaDeep().getUnit());
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        System.out.println(
                "==============================================================================");
    }
}
