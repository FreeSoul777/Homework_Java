package lab_3.Logic;

import lab_3.Field.Map;
import lab_3.Structure.Mob;
import lab_3.Structure.Player;

import java.util.ArrayList;

public class GameLoop {

    public static final String RESET = "\033[0m";
    public static final String GREEN_BOLD = "\033[1;32m";
    public static final String RED_BOLD = "\033[1;31m";


    static public Player player;
    static public ArrayList<Mob> masMob;
    static public Map map = new Map();
    static CreatureController creatureController;
    static PlayerController playerController;

    public static void main(String[] args) {

        Thread thread;

        player = Creation.createHero();
        masMob = Creation.createMobs();

        showHero();
        showCreatures();

        for(Mob mob: masMob) {
            mob.setCell(map.assignACell('m'), 'm');
            mob.getCell().add(mob);
            creatureController = new CreatureController(mob);
            thread = new Thread(creatureController);
            mob.setCreatureController(creatureController);
            thread.start();
        }

        player.setCell(map.assignACell('@'), '@');
        player.getCell().add(player);
        playerController = new PlayerController(player);
        thread = new Thread(playerController);
        player.setPlayerController(playerController);
        thread.start();

        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for(Mob mob: masMob) {
            System.out.print(mob.getName() + " ");
            mob.getCreatureController().setAlive(false);
        }

        GameLogic.gameOver();
    }

    public static void showHero() {
        System.out.print(GREEN_BOLD + "Главный герой: " + RESET);
        player.show();
    }

    public static void showCreatures() {
        System.out.print(RED_BOLD + "Существа и их свойства: " + RESET);
        for (Mob m : masMob) {
            m.show();
        }
    }
}
