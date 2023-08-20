package lab_3.Structure;

import lab_3.Field.Cell;

public class Creatures extends GameObject {
    private int health = 0, defence = 0, powerAttack = 0;
    private Cell cell;

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getDefence() {
        return defence;
    }

    public void setDefence(int defence) {
        this.defence = defence;
    }

    public int getPowerAttack() {
        return powerAttack;
    }

    public void setPowerAttack(int powerAttack) {
        this.powerAttack = powerAttack;
    }

    public void setCell(Cell cell, char symbol) {
        this.cell = cell;
        this.cell.setSymbol(symbol);
    }

    public Cell getCell() {
        return cell;
    }

    public boolean isDead() {
        return getHealth() <= 0 ;
    }

    public Creatures(String name) {
        super(name);
    }

}
