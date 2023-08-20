package lab_5;

import java.io.Serializable;

import static lab_5.Logic.View.MAGENTA_BOLD;
import static lab_5.Logic.View.RESET;

public class Location implements Serializable {

    private NPC npc;
    private int level;
    private String description;

    public Location(NPC npc, int level, String description) {
        this.npc = npc;
        this.level = level;
        this.description = description;
    }

    public Location() {
        this.npc = new NPC();
        this.level = 0;
        this.description = "";
    }

    public NPC getNpc() {
        return npc;
    }

    public void setNpc(NPC npc) {
        this.npc = npc;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return new StringBuffer(MAGENTA_BOLD + "\nDescription : " + RESET).append(this.description)
                .append(MAGENTA_BOLD + "\nLevel : " + RESET).append(this.level)
                .append(MAGENTA_BOLD + "\nlab_5.NPC : " + RESET).append(this.npc).toString();
    }
}

