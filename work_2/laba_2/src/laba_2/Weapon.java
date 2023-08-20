package laba_2;

import laba_2.Structure.Damager;
import laba_2.Structure.Equipment;

public class Weapon extends Equipment implements Damager {

    private String name;
    private int coast, needLevel, attack;

    public int getNeed_level() {
        return needLevel;
    }

    public Weapon(String name, int attack, int coast, int needLevel) {
        super();
        this.name = name;
        this.attack = attack;
        this.coast = coast;
        this.needLevel = needLevel;
    }

    public Weapon() {
        name = "Weapon";
        attack = coast = needLevel = 0;
    }


    public void show() {
        System.out.println(
                "\nWeapon: " + name + ", ID: " + id + ", attack: " + attack +
                        ", coast: " + coast + ", needLevel: " + needLevel
        );
    }

    @Override
    public int damage() {
        return attack;
    }

}
