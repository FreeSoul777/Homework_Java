package lab_3.Field;

import lab_3.Logic.GameLogic;
import lab_3.Structure.GameObject;
import lab_3.Structure.Mob;
import lab_3.Structure.Player;

import java.text.MessageFormat;
import java.util.ArrayList;

public class Cell {

    private Position position;
    private char symbol;
    private ArrayList<GameObject> listObjects = new ArrayList<>();
    private int countMobsOnCell = 0;
    private int countPlayerOnCell = 0;


    public Cell(int x, int y, char symbol) {
        position = new Position(x, y);
        this.symbol = symbol;
    }

    public void show() {
        System.out.print(symbol);
    }

    public char getSymbol() {
        return symbol;
    }

    public void setSymbol(char symbol) {
        this.symbol = symbol;
    }

    public void add(GameObject object) {
        listObjects.add(object);
        if(object.getClass().equals(Mob.class))
            countMobsOnCell += 1;
        if(object.getClass().equals(Player.class))
            countPlayerOnCell += 1;
    }

    public void remove(GameObject object) {
        if(object.getClass().equals(Player.class))
            countPlayerOnCell -= 1;
        else if(object.getClass().equals(Mob.class))
            countMobsOnCell -= 1;
        listObjects.remove(object);

        if(countPlayerOnCell > 0) symbol = '@';
        else if(countMobsOnCell > 0) symbol = 'm';
        else symbol = '*';

        if(object.getClass().equals(Mob.class)) {
            if(((Mob) object).getExtraPower()) {
                OnOfExtraPower();
            }
        }
    }

    public ArrayList<GameObject> getObject() {
        return listObjects;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(int x, int y) {
        this.position = new Position(x, y);
    }

    public int checkCountMobs() {
        return countMobsOnCell;
    }

    public int checkPlayer() {
        return countPlayerOnCell;
    }

    public Mob choosingTheMostPowerful() {
        Mob mob = null;
        for(GameObject ob: listObjects) {
            if(ob.getClass().equals(Mob.class)) {
                if(mob == null) {
                    mob = (Mob) ob;
                    continue;
                }
                if(GameLogic.getDPS((Mob) ob, ((Mob) ob).getEquipmentWeapon()) >
                        GameLogic.getDPS(mob, mob.getEquipmentWeapon()) ) {
                    mob = (Mob) ob;
                }
            }
        }
        return mob;
    }

    public void OnOfExtraPower() {
        boolean flag = false;
        for(GameObject ob: listObjects) {
            if(ob.getClass().equals(Mob.class)) {
                ((Mob) ob).OnOffExtraPower();
                flag = ((Mob) ob).getExtraPower();
            }
        }
        System.out.println(MessageFormat.format("У мобов на {0} [{1};{2}] ",
                this.getClass().getSimpleName(), getPosition().getX(), getPosition().getY()) +
                (flag ? " повысилась сила на 1" : "понизилась сила на -1"));
    }
}
