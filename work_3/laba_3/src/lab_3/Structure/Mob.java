package lab_3.Structure;

import lab_3.Logic.CreatureController;
import lab_3.Logic.GameLogic;
import lab_3.Loot.Armor;
import lab_3.Loot.EquipmentCell;
import lab_3.Loot.Item;
import lab_3.Loot.Weapon;

import java.util.ArrayList;
import java.util.List;

public class Mob extends Creatures implements Lootable, Attacker, Damageable{
    private EquipmentCell<Armor> armor;
    private EquipmentCell<Weapon> weapon;
    private boolean extraPower = false;
    private CreatureController creatureController;

    public Mob(String name, int health , int powerAttack, int defence) {
        super(name);
        setHealth(health);
        setPowerAttack(powerAttack);
        setDefence(defence);
    }

    public Mob() {
        super("Enemy");
    }

    public void show() {
        System.out.println(
                "\nEnemy: " + getName() + ", ID: " + id + ", health : " + getHealth()  +
                        ", powerAttack: " + getPowerAttack() + ", defence: " + getDefence()
        );
        weapon.getValue().show();
        armor.getValue().show();
    }

    public CreatureController getCreatureController() {
        return creatureController;
    }

    public void setCreatureController(CreatureController creatureController) {
        this.creatureController = creatureController;
    }

    public void setWeapon(Weapon we) {
        weapon = new EquipmentCell<>(we);
    }

    public void setArmor(Armor ar) {
        armor = new EquipmentCell<>(ar);
    }

    @Override
    public List<Item> dropLoot() {
        List<Item> items = new ArrayList<>();
        items.add(weapon.getValue());
        items.add(armor.getValue());
        return items;
    }

    @Override
    public void attack(Damageable target) {
        int DPS = GameLogic.getDPS(this, weapon.getValue());
        GameLogic.printMassageOfBattle(this, (Creatures) target, DPS);
        target.getHit(DPS);
    }

    @Override
    public void getHit(int DPS) {
        int defence = getDefence();
        if(defence - DPS > 0)
            setDefence(defence - DPS);
        else {
            setHealth(getHealth() - DPS + defence);
            setDefence(0);
        }
    }

    public void OnOffExtraPower() {
        extraPower = !extraPower;
        int p = getPowerAttack();
        p += extraPower ? 1 : -1;
        setPowerAttack(p);
    }

    public boolean getExtraPower() {
        return extraPower;
    }

    public Weapon getEquipmentWeapon() {
        return weapon.getValue();
    }

    public Armor getEquipmentArmor() {
        return armor.getValue();
    }

}
