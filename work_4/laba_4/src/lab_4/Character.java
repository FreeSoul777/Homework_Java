package lab_4;

import java.util.Objects;

public class Character {

    private String name;
    private int hp;
    private int atk;
    private int def;
    private int elementalMastery;
    private int stamina;
    private String archon;
    private int energyRecharge;

    public int getEnergyRecharge() {
        return energyRecharge;
    }

    public void setEnergyRecharge(int energyRecharge) {
        this.energyRecharge = energyRecharge;
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

    public int getAtk() {
        return atk;
    }

    public void setAtk(int atk) {
        this.atk = atk;
    }

    public int getDef() {
        return def;
    }

    public void setDef(int def) {
        this.def = def;
    }

    public int getElementalMastery() {
        return elementalMastery;
    }

    public void setElementalMastery(int elementalMastery) {
        this.elementalMastery = elementalMastery;
    }

    public int getStamina() {
        return stamina;
    }

    public void setStamina(int stamina) {
        this.stamina = stamina;
    }

    public String getArchon() {
        return archon;
    }

    public void setArchon(String archon) {
        this.archon = archon;
    }

    public Character(String name, int hp, int atk, int def, int elementalMastery, int stamina, String archon, int energyRecharge) {
        this.name = name;
        this.hp = hp;
        this.atk = atk;
        this.def = def;
        this.elementalMastery = elementalMastery;
        this.stamina = stamina;
        this.archon = archon;
        this.energyRecharge = energyRecharge;
    }

    public Character() {
        name = "Name";
        archon = "Бродяга";
        hp = atk = def = elementalMastery = stamina = energyRecharge = 10;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Character character = (Character) o;
        return  def == character.def
                && hp == character.hp
                && atk == character.atk
                && Objects.equals(archon, character.archon)
                && energyRecharge == character.energyRecharge
                && elementalMastery == character.elementalMastery
                && stamina == character.stamina
                && Objects.equals(name, character.name);
    }

    @Override
    public String toString() {
        return new String()
                .concat(/*"Name: " +*/ getName())
                .concat("\n")
                .concat("HP: " + getHp())
                .concat("\n")
                .concat("ATK: " + getAtk())
                .concat("\n")
                .concat("DEF: " + getDef())
                .concat("\n")
                .concat("Archon: " + getArchon())
                .concat("\n")
                .concat("EnergyRecharge: " + getEnergyRecharge())
                .concat("\n")
                .concat("ElementalMastery: " + getElementalMastery())
                .concat("\n")
                .concat("Stamina: " + getStamina())
                .concat("\n---------------------------\n");
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, hp, atk, def, archon, energyRecharge, elementalMastery, stamina);
    }
}
