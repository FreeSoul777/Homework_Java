package lab_3.Logic;

import lab_3.Field.Position;
import lab_3.Structure.Mob;
import lab_3.Structure.Player;

import java.text.MessageFormat;
import java.util.concurrent.TimeUnit;

import static lab_3.Field.Map.SIZE_X;
import static lab_3.Field.Map.SIZE_Y;
import static lab_3.Logic.Creation.rnd;
import static lab_3.Logic.GameLoop.*;

public class PlayerController implements Runnable {

    private Player player;
    private static final int SLEEP = 5;
    private boolean isAlive = true;

    PlayerController(Player player) {
        this.player = player;
    }

    public void setAlive(boolean live) {
        isAlive = live;
    }

    public boolean getAlive() {
        return isAlive;
    }

    @Override
    public void run() {
        System.out.println(GREEN_BOLD + player.getName() + " - run!!!!" + RESET);
        while(isAlive) {
            sleep();
            while(!player.isDead()) {
                if(!checkCell()) break;
                Mob mob = player.getCell().choosingTheMostPowerful();
                GameLogic.battle(mob);
                if(mob.isDead()) {
                    mob.getCreatureController().resumeMob();
                    sleep();
                }
            }
            if(player.isDead() || GameLogic.getSizeMasMob() == 0) {
                isAlive = false;
                player.getCell().remove(player);
            }
            else {
                nextStep();
                map.show();

            }
        }
        System.out.println(RED_BOLD + player.getName() + " is DEAD" + RESET);
    }

    public synchronized void waitPlayer() {
        try {
            wait();
        }
        catch (InterruptedException e) {
            System.out.println(player.getName() + " - Error wait!!!!");
        }
    }

    public synchronized void resumePlayer() {
        notify();
    }

    void sleep() {
        try {
            TimeUnit.SECONDS.sleep(SLEEP);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    boolean checkCell() {
        return player.getCell().checkCountMobs() > 0;
    }

    void nextStep() {
        Position oldPos = player.getCell().getPosition();
        int x = oldPos.getX();
        int y = oldPos.getY();
        if (x > 0 && x < SIZE_X - 1) x += rnd.nextInt(3) - 1;
        else if (x == 0) x += rnd.nextInt(2);
        else if (x == SIZE_X - 1) x -= rnd.nextInt(2);

        if (y > 0 && y < SIZE_Y - 1) y += rnd.nextInt(3) - 1;
        else if (y == 0) y += rnd.nextInt(2);
        else if (y == SIZE_Y - 1) y -= rnd.nextInt(2);

        Position newPos = new Position(x, y);
        if (newPos.getY() == oldPos.getY() && newPos.getX() == oldPos.getX());
        else {
            System.out.println(MessageFormat.format("{0} {1} moving from ({2};{3}) to ({4};{5})",
                    player.getClass().getSimpleName(), player.getName(), oldPos.getX(),
                    oldPos.getY(), newPos.getX(), newPos.getY()));
            player.getCell().remove(player);
            int i = map.findCell(newPos);
            if(i == -1) return;
            player.setCell(map.get(i), '@');
            player.getCell().add(player);
        }
    }
}

