package lab_3.Structure;

import lab_3.Logic.GameLogic;
import lab_3.Logic.PlayerController;
import lab_3.Loot.*;

import java.util.List;

public class Player extends Creatures implements Attacker, Damageable{
    private int  experience, level;
    EquipmentCell<Armor> armor;
    EquipmentCell<Weapon> weapon;
    Inventory<Item> inv = new Inventory<>();
    private PlayerController playerController;

    public Player(String name, int health , int experience, int level, int powerAttack, int defence) {
        super(name);
        setHealth(health);
        setPowerAttack(powerAttack);
        setDefence(defence);
        this.experience = experience;
        this.level = level;
    }

    public Player() {
        super("Hero");
    }

    public boolean takeALoot(int levelLoot) {
        if (level >= levelLoot) {
            System.out.println("Персонаж может взять оружие!\n");
            return true;
        } else {
            System.out.println("Персонаж не может взять оружие!\n");
            return false;
        }
    }

    public void setWeapon(Weapon we) {
        weapon = new EquipmentCell<>(we);
        addItem(we);
    }

    public void setArmor(Armor ar) {
        armor = new EquipmentCell<>(ar);
        addItem(ar);
    }

    public void addItem(Item item) {
        inv.add(item);
    }

    public void addAll(List<Item> lst) {
        inv.addAll(lst);

        int size = inv.getSize();
        ChangeLoot(inv.get(size - 2));
        ChangeLoot(inv.get(size - 1));
    }

    public <T> void ChangeLoot(T equipment) {
        Class clazz = equipment.getClass();
        if(clazz == Weapon.class.getClass()) {
            Weapon weapon = (Weapon) equipment;
            if (weapon.getAPS() * weapon.getAPS()
                    > this.weapon.getValue().getAPS() * this.weapon.getValue().getAttack()
                    && takeALoot(weapon.getNeedLevel())) {
                this.weapon.setValue(weapon);
            }
        }
        else if(clazz == Armor.class.getClass()) {
            Armor armor = (Armor) equipment;
            if (armor.getDefence() > this.armor.getValue().getDefence()
                    && takeALoot(armor.getNeedLevel())) {
                this.armor.setValue(armor);
            }
        }
    }

    public void show() {
        System.out.println(
                "\nPlayer: " + getName() + ", ID: " + id + ", health : " +
                        getHealth()  + ", experience: " + experience
                        + ", level: " + level + ", powerAttack: " +
                        getPowerAttack() + ", defence: " + getDefence()
        );
        weapon.getValue().show();
        armor.getValue().show();
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

    public PlayerController getPlayerController() {
        return playerController;
    }

    public void setPlayerController(PlayerController playerController) {
        this.playerController = playerController;
    }

    public Weapon getEquipmentWeapon() {
        return weapon.getValue();
    }

    public Armor getEquipmentArmor() {
        return armor.getValue();
    }

}

