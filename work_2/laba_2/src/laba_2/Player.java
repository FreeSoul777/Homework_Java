package laba_2;

import laba_2.Structure.*;

import java.util.List;

public class Player extends Creatures implements Attacker, Damageable {
    private int  experience, level;
    EquipmentCell<Equipment> loot = null;
    Inventory<Item> inv = new Inventory<>();

    public Player(String name, int health , int experience, int level, int powerAttack, int defence) {
        super();
        this.name = name;
        this.health  = health ;
        this.experience = experience;
        this.level = level;
        this.powerAttack = powerAttack;
        this.defence = defence;
    }

    public Player() {
        name = "Hero";
        health  = id = experience = level = powerAttack = defence = 0;
    }

    public boolean takeAWeapon(int levelWeapon) {

        if (level >= levelWeapon) {
            System.out.println("Персонаж может взять оружие!\n");
            return true;
        } else {
            System.out.println("Персонаж не может поднять оружие!\n");
            return false;
        }
    }

    public void addWeapon(Equipment weapon) {
        loot = new EquipmentCell<>(weapon);
        addItem(weapon);
    }

    public void addItem(Item item) {
        inv.add(item);
    }

    public void addAll(List<Item> lst) {
        inv.addAll(lst);
    }

    public void show() {
        System.out.println(
                "\nPlayer: " + name + ", ID: " + id + ", health : " + health  + ", experience: " + experience
                        + ", level: " + level + ", powerAttack: " + powerAttack + ", defence: " + defence
        );
    }

    @Override
    public void attack(Damageable target) {
        if(Damager.class.isAssignableFrom(loot.getValue().getClass())
        ) {
            Damager damager = (Damager) loot.getValue();
            target.getHit(damager);
        }
        else {
            System.out.println("У атакующего нет оружия");
        }
    }

    @Override
    public void getHit(Damager damager) {
        if(defence - damager.damage() > 0)
            defence -= damager.damage();
        else {
            health -= damager.damage() + defence;
            defence = 0;
        }
    }

}
