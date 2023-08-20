package lab_3.Loot;

import lab_3.Structure.Damager;

public class Weapon extends Equipment implements Damager {

    private int coast, attack, aps;

    public Weapon(String name, int attack, int coast, int needLevel, int aps) {
        super(name);
        this.attack = attack;
        this.coast = coast;
        this.aps = aps;
        setNeedLevel(needLevel);
    }

    public Weapon() {
        super("Weapon");
        attack = coast = 0;
    }


    public void show() {
        System.out.println(
                "\nWeapon: " + getName() + ", ID: " + id + ", attack: " + attack +
                        ", coast: " + coast + ", needLevel: " + getNeedLevel()
        );
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    @Override
    public int getAPS() {
        return aps;
    }

    @Override
    public int damage() {
        return attack;
    }
}

