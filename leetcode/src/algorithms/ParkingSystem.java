package algorithms;

/**
 * 设计停车系统
 *
 * <p>Your ParkingSystem object will be instantiated and called as such:
 *
 * <p>ParkingSystem obj = new ParkingSystem(big, medium, small);
 *
 * <p>boolean param_1 = obj.addCar(carType);
 *
 * @author samin
 * @date 2021-01-11
 */
public class ParkingSystem {

    private int big;
    private int medium;
    private int small;

    public ParkingSystem(int big, int medium, int small) {
        this.big = big;
        this.medium = medium;
        this.small = small;
    }

    public static void main(String[] args) {
        // true true false false
        ParkingSystem ps = new ParkingSystem(1, 1, 0);
        System.out.println(ps.addCar(1));
        System.out.println(ps.addCar(2));
        System.out.println(ps.addCar(3));
        System.out.println(ps.addCar(1));
    }

    public boolean addCar(int carType) {
        switch (carType) {
            case 1:
                if (big > 0) {
                    big = big - 1;
                    return true;
                } else {
                    return false;
                }
            case 2:
                if (medium > 0) {
                    medium = medium - 1;
                    return true;
                } else {
                    return false;
                }
            case 3:
                if (small > 0) {
                    small = small - 1;
                    return true;
                } else {
                    return false;
                }
            default:
                return false;
        }
    }
}
