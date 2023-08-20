package laba_2;

import laba_2.Structure.*;

import java.util.List;

public class Mob extends Creatures implements Lootable, Attacker, Damageable {
    EquipmentCell<Equipment> loot;
    Inventory<Item> inv = new Inventory<>();

    public Mob(String name, int health , int powerAttack, int defence) {
        super();
        this.name = name;
        this.health  = health ;
        this.id = id;
        this.powerAttack = powerAttack;
        this.defence = defence;
    }

    public Mob() {
        name = "Enemy";
        health  = id = powerAttack = defence = 0;
    }

    public void show() {
        System.out.println(
                "\nEnemy: " + name + ", ID: " + id + ", health : " + health  + ", powerAttack: " +
                        powerAttack + ", defence: " + defence
        );
    }

    public void addWeapon(Equipment weapon) {
        loot = new EquipmentCell<>(weapon);
        addItem(weapon);
    }

    public void addItem(Item item) {
        inv.add(item);
    }

    @Override
    public List<Item> dropLoot() {
        return inv.getList();
    }

    @Override
    public void attack(Damageable target) {
        if(Damager.class.isAssignableFrom(loot.getValue().getClass())) {
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
