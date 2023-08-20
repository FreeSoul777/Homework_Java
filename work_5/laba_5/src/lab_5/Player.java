package lab_5;

import java.io.Serializable;

public class Player implements Serializable {

    private String name;
    private int hp;

    public Player(String name, int hp) {
        this.name = name;
        this.hp = hp;
    }

    public Player() {
        this.name = "Name";
        this.hp = 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public boolean isDead(int result) {
        hp += result;
        return hp > 0;
    }

    @Override
    public String toString() {
        return new StringBuffer("\nName : ").append(this.name)
                .append("\nHp : ").append(this.hp).toString();
    }
}
