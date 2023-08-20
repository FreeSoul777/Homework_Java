package laba_2.Logic;

import laba_2.Mob;
import laba_2.Player;
import laba_2.Weapon;

import java.util.ArrayList;
import java.util.Random;

import static laba_2.Logic.GameLogic.fight;

public class GameLoop {

    static public Player player = new Player("Player", 70, 70, 100, 50, 80);
    static public ArrayList<Mob> masMob = new ArrayList<>();
    static public ArrayList<Weapon> masWeapon = new ArrayList<>();

    public static void main(String[] args) {

        makeMob(); // создаем врагов
        makeWeapons(); // создаем оружие

        run();

    }

    public static void run () {
        Random rnd = new Random();
        for(int i = 0; i < 10; i++) {
            if(player.takeAWeapon(masWeapon.get(i).getNeed_level())) {
                player.addWeapon(masWeapon.get(i));
                break;
            }
        }

        for(Mob mob: masMob) {
            player.show();
            mob.show();
            mob.addWeapon(masWeapon.get(rnd.nextInt((10) + 0)));
            while (player.getHealth() > 0 && mob.getHealth() > 0) {
                fight(player, mob);
                System.out.println("fight player vs mob");
                player.show();
                mob.show();
                if (mob.getHealth() > 0) {
                    fight(mob, player);
                    System.out.println("fight mob vs player");
                    player.show();
                    mob.show();
                }
                else {
                    System.out.println("Win");
                    player.addAll(mob.dropLoot());
                    break;
                }
            }

            if(player.getHealth() <= 0) {
                System.out.println("End");
                break;
            }
        }
    }

    public static void makeMob() {
        Random rnd = new Random();

        for (int i = 0; i < 10; i++) {
            String name = "Mob №" + rnd.nextInt((100) + 1);
            int hp = rnd.nextInt((100) + 1);
            int attack = rnd.nextInt((100) + 1);
            int defence = rnd.nextInt((100) + 1);
            masMob.add(new Mob(name, hp, attack, defence));
        }
    }

    public static void makeWeapons() {
        String[] names = {"Glock", "AK-47", "RX-22", "EP 22", "SPAS-12", "Stinger", "AT4", "RPG-7", "M9", "TMP"};
        Random rnd = new Random();

        for (int i = 0; i < 20; i++) {
            String name = names[rnd.nextInt(10)];
            int attack = rnd.nextInt((100) + 1);
            int coast = rnd.nextInt((100) + 1);
            int needLevel = rnd.nextInt((100) + 1);
            masWeapon.add(new Weapon(name, attack, coast, needLevel));
        }
    }
}
