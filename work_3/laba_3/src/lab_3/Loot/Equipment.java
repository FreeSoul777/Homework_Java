package lab_3.Loot;

public class Equipment extends Item {

    private int needLevel = 0;

    public Equipment(String name) {
        super(name);
    }
    public int getNeedLevel() {
        return needLevel;
    }

    public void setNeedLevel(int needLevel) {
        this.needLevel = needLevel;
    }
}
