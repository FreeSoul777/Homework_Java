package lab_3.Loot;

public class Armor extends Equipment {

    private int coast, defence;

    public Armor(String name, int defence, int coast, int needLevel) {
        super(name);
        this.defence = defence;
        this.coast = coast;
        setNeedLevel(needLevel);
    }

    public Armor() {
        super("Weapon");
        defence = coast = 0;
    }


    public void show() {
        System.out.println(
                "\nArmor: " + getName() + ", ID: " + id + ", defence: " + defence +
                        ", coast: " + coast + ", needLevel: " + getNeedLevel()
        );
    }

    public int getDefence() {
        return defence;
    }

    public void setDefence(int defence) {
        this.defence = defence;
    }
}
