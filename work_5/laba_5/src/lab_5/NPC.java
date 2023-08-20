package lab_5;

import lab_5.Log.Log;
import lab_5.Logic.GameLoop;
import lab_5.Logic.Input;
import lab_5.Logic.Riddle;
import lab_5.Logic.View;

import java.io.Serializable;

import static lab_5.Logic.View.*;

public class NPC implements Serializable {

    private String name;
    private Riddle riddle;

    public NPC(String name, Riddle riddle) {
        this.name = name;
        this.riddle = riddle;
    }

    public NPC() {
        this.name = "Name";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Riddle getRiddle() {
        return riddle;
    }

    public void setRiddle(Riddle riddle) {
        this.riddle = riddle;
    }

    public boolean toTalk() {
        Log.writeInto("lab_5.NPC: " + "toTalk()");
        View.printlnMessage(BrightCyan_BOLD + "Привет, " + GameLoop.player.getName() + "!" +
                " Меня зовут " + getName() + ". У меня для тебя есть загадка. Ее сложность: " + RESET +
                BrightYellow_BOLD + riddle.getComplexity() + "." + RESET);
        View.printlnMessage(riddle.getRiddle());
        View.printlnMessage(BrightCyan_BOLD + riddle.getAllAnswers() + RESET);

        int answer = Input.inputAnswer();

        if(answer == riddle.getTrueNumber()) {
            View.printlnMessage(BrightCyan_BOLD + "И ответ " + answer + " абсолютно правильный." +
                    " Ты заработал очки: " + RESET + GREEN_BOLD + riddle.getComplexity() + "" + RESET);
            return GameLoop.player.isDead(riddle.getComplexity());
        }
        else {
            View.printlnMessage(BrightCyan_BOLD + "И ответ " + answer + " неверный." +
                    " Ты заработал очки: " + RESET + RED_BOLD + -riddle.getComplexity() + "" + RESET);
            return GameLoop.player.isDead(-riddle.getComplexity());
        }
    }


    @Override
    public String toString() {
        return new StringBuffer("\nName : ").append(this.name)
                .append(MAGENTA_BOLD + "\nlab_5.Logic.Riddle : " + RESET).append(this.riddle).toString();
    }
}
