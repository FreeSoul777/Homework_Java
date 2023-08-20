package lab_3.Logic;

import lab_3.Structure.*;

import java.text.MessageFormat;

import static lab_3.Logic.GameLoop.*;

public class GameLogic {

    public static void fight(Attacker attacker, Damageable target) {
        attacker.attack(target);
    }

    public static void battle (Mob mob) {
        while (!player.isDead() && !mob.isDead()) {
            GameLogic.fight(player, mob);
            if (!mob.isDead()) {
                GameLogic.fight(mob, player);
            }
            else {
                player.addAll(mob.dropLoot());
            }
        }

        System.out.println(MessageFormat.format("{0} {1} is ",
                player.getClass().getSimpleName(), player.getName()) +
                (player.isDead() ? "DEAD" : "WIN"));

    }

    public static void gameOver(){
        System.out.println(GREEN_BOLD + "END GAME!" + RESET);
    }

    public static int getDPS(Creatures creature, Damager damager) {
        int atk = creature.getPowerAttack();
        int weaponDamage = damager.damage();
        int aps = damager.getAPS();

        return (atk + weaponDamage) * aps;
    }

    public static void printMassageOfBattle(Creatures creatures1, Creatures creatures2, int damage) {
        String ownName1 = creatures1.getOwnClassName();
        String ownName2 = creatures2.getOwnClassName();
        String name1 = creatures1.getName();
        String name2 = creatures2.getName();

        String string = MessageFormat.format("{0} {1}: deals damage {2} {3} {4}",
                ownName1, name1, damage, ownName2, name2);
        System.out.print(RED_BOLD + "FIGHT: " + RESET);
        System.out.println(string);
    }

    public static void removeMasMOb(Mob mob) {
        masMob.remove(mob);
    }

    public static int getSizeMasMob() {
        return masMob.size();
    }
}

