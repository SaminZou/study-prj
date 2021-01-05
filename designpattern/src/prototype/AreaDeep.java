package prototype;

public class AreaDeep implements Cloneable {

    // 钞票单位
    private String unit;

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    @Override
    protected AreaDeep clone() throws CloneNotSupportedException {
        AreaDeep cloneArea;
        cloneArea = (AreaDeep) super.clone();
        return cloneArea;
    }
}
