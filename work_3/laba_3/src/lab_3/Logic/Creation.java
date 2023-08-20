package lab_3.Logic;

import lab_3.Loot.Armor;
import lab_3.Loot.Weapon;
import lab_3.Structure.Mob;
import lab_3.Structure.Player;

import java.util.ArrayList;
import java.util.Random;

public class Creation {

    static public Random rnd = new Random();
    static private ArrayList<Weapon> masWeapon = makeWeapons();
    static private ArrayList<Armor> masArmor = makeArmors();

    public static Player createHero() {
        String name = "Player";
        Weapon weapon = ChooseAWeapon();
        Armor armor = ChooseAArmor();
        int hp = rnd.nextInt((100) + 1);
        int pAttack = rnd.nextInt((100) + 1);
        int def = rnd.nextInt((100) + 1);
        int exp = rnd.nextInt((100) + 1);
        int level = rnd.nextInt((100) + 1);

        Player player = new Player(name, hp, exp, level, pAttack, def);
        player.setArmor(armor);
        player.setWeapon(weapon);

        return player;
    }

    public static ArrayList<Mob> createMobs() {
        ArrayList<Mob> mobs = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Weapon weapon = masWeapon.get(rnd.nextInt(20));
            Armor armor = masArmor.get(rnd.nextInt(20));
            String name = "Mob №" + i;
            int hp = rnd.nextInt((100) + 1);
            int attack = rnd.nextInt((100) + 1);
            int defence = rnd.nextInt((100) + 1);

            mobs.add(new Mob(name, hp, attack, defence));
            mobs.get(i).setWeapon(weapon);
            mobs.get(i).setArmor(armor);
        }
        return mobs;
    }

    private static ArrayList<Weapon> makeWeapons() {
        String[] names = {"Glock", "AK-47", "RX-22", "EP 22", "SPAS-12", "Stinger", "AT4", "RPG-7", "M9", "TMP"};
        ArrayList<Weapon> weapons = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            String name = names[rnd.nextInt(10)];
            int attack = rnd.nextInt((100) + 1);
            int coast = rnd.nextInt((100) + 1);
            int needLevel = rnd.nextInt((100) + 1);
            weapons.add(new Weapon(name, attack, coast, needLevel, 1));
        }
        return weapons;
    }

    private static ArrayList<Armor> makeArmors() {
        ArrayList<Armor> armors = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            String name = "Armor " + i;
            int def = rnd.nextInt((100) + 1);
            int coast = rnd.nextInt((100) + 1);
            int needLevel = rnd.nextInt((100) + 1);
            armors.add(new Armor(name, def, coast, needLevel));
        }
        return armors;
    }

    public static Weapon ChooseAWeapon() {
        return masWeapon.get(rnd.nextInt(20));
    }

    public static Armor ChooseAArmor() {
        return masArmor.get(rnd.nextInt(20));
    }
}

