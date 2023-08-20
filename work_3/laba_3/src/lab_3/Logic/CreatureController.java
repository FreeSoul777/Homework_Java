package lab_3.Logic;

import lab_3.Field.Position;
import lab_3.Structure.Creatures;
import lab_3.Structure.Mob;

import java.text.MessageFormat;
import java.util.concurrent.TimeUnit;

import static lab_3.Field.Map.SIZE_X;
import static lab_3.Field.Map.SIZE_Y;
import static lab_3.Logic.Creation.rnd;
import static lab_3.Logic.GameLoop.*;

public class CreatureController implements Runnable{

    private Mob mob;
    private static final int SLEEP = 5;
    private boolean isAlive = true;

    CreatureController(Creatures creature) {
        mob = (Mob) creature;
    }

    public void setAlive(boolean live) {
        isAlive = live;
        return;
    }

    public boolean getAlive() {
        return isAlive;
    }

    @Override
    public void run() {
        System.out.println(GREEN_BOLD + mob.getName() + " - run!!!!" + RESET);
        while(isAlive) {
            sleep();
            if(checkCell()) {
                waitMob();
            }
            if(mob.isDead()) {
                isAlive = false;
                mob.getCell().remove(mob);
            }
            else {
                nextStep();
            }
        }
        System.out.println(RED_BOLD + mob.getName() + " is DEAD" + RESET);
        GameLogic.removeMasMOb(mob);
    }

    public synchronized void waitMob() {
        try {
            wait();
        }
        catch (InterruptedException e) {
            System.out.println(mob.getName() + " - Error wait!!!!");
        }
    }

    public synchronized void resumeMob() {
        notify();
    }

    boolean checkCell() {
        if(mob.getCell().checkCountMobs() > 1) {
            mob.getCell().OnOfExtraPower();
        }
        return mob.getCell().checkPlayer() > 0;
    }

    void sleep() {
        try {
            TimeUnit.SECONDS.sleep(SLEEP);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    void nextStep() {
        Position oldPos = mob.getCell().getPosition();
        int x = oldPos.getX();
        int y = oldPos.getY();
        if (x > 0 && x < SIZE_X - 1) x += rnd.nextInt(3) - 1;
        else if (x == 0) x += rnd.nextInt(2);
        else if (x == SIZE_X - 1) x += rnd.nextInt(2) - 1;

        if (y > 0 && y < SIZE_Y - 1) y += rnd.nextInt(3) - 1;
        else if (y == 0) y += rnd.nextInt(2);
        else if (y == SIZE_Y - 1) y += rnd.nextInt(2) - 1;

        Position newPos = new Position(x, y);
        if (newPos.getY() == oldPos.getY() && newPos.getX() == oldPos.getX());
        else {
            System.out.println(MessageFormat.format("{0} {1} moving from ({2};{3}) to ({4};{5})",
                    mob.getClass().getSimpleName(), mob.getName(), oldPos.getX(),
                    oldPos.getY(), newPos.getX(), newPos.getY()));
            mob.getCell().remove(mob);
            int i = GameLoop.map.findCell(newPos);
            if(i == -1) return;
            mob.setCell(GameLoop.map.get(i), 'm');
            mob.getCell().add(mob);
        }
    }
}
