package lab_5.Logic;

import lab_5.*;
import lab_5.Log.AutoSaveLogWorker;
import lab_5.Log.Log;

import java.util.ArrayList;

import static lab_5.Logic.SaveLoad.deserializeLocation;
import static lab_5.Logic.SaveLoad.serializeLocation;
import static lab_5.Logic.View.*;

public class GameLoop {

    static public ArrayList<Location> locations = new ArrayList<>();
    static public Player player = new Player();

    public static void main(String[] args) {
        AutoSaveLogWorker autoSaveLogWorker = new AutoSaveLogWorker();
        Thread thread = new Thread(autoSaveLogWorker);
        thread.start();

        Create create = new Create();
        boolean save = deserializeLocation();
        ArrayList<Location> list = create.createLocation();
        boolean flag = false;

        if(save) {
            flag = true;
            View.printlnMessage(player.toString());
            flag = getAnswer(BrightYellow_BOLD + "Продолжить игру с этими данными? (1/0) :" + RESET);
        }
        if(!flag) {
            flag = getAnswer(BrightYellow_BOLD + "Хотите начать новую игру? (1/0) :" + RESET);
            if (flag) {
                player = create.createPlayer();
                locations = create.randomLocation(10, list);
                locations = create.sortedLocation(locations);

                View.printlnMessage(player.toString());
            } else {
                endGame();
            }
        }

        flag = player.isDead(0);
        while (flag) {
            View.printlnMessage(MAGENTA_BOLD + "\n\nПриготовься, начинается игра!" + RESET);
            if(!save) {
                locations = create.randomLocation(10, list);
                locations = create.sortedLocation(locations);
            }

            for(int i = 0; i < 10 && flag; i++) {
                Log.writeInto("lab_5.Logic.GameLoop: " + "main(), i = " + i);
                View.printlnMessage("\nТы попал в: " + MAGENTA_BOLD + locations.get(i).getDescription() + RESET);
                flag = locations.get(i).getNpc().toTalk();
                View.printlnMessage("Твое здоровье после локации: " +
                        (flag ? GREEN_BOLD : RED_BOLD) + player.getHp() + RESET);
            }

            if(flag) {
                View.printlnMessage(GREEN_BOLD +
                        "Ты прошел очередной круг загадок и до сих пор жив, мои поздравления!" + RESET);
                flag = getAnswer(BrightYellow_BOLD + "Хочешь продолжить ? (1/0) : " + RESET);
            }
            else {
                View.printlnMessage(RED_BOLD + "К сожалению, этот круг для тебя оказался последним." + RESET);
            }
            save = false;
        }

        endGame();
    }

    public static boolean getAnswer(String message) {
        boolean flag = true;
        while (true) {
            View.printlnMessage(message);
            int answer = Input.inputAnswer();
            if (answer == 0) {
                flag = false;
                break;
            } else if (answer == 1) {
                flag = true;
                break;
            } else {
                View.printlnMessage("Не пойму твой ответ, попробуй еще раз.");
            }
        }
        return flag;
    }

    public static void endGame() {
        Log.writeInto("lab_5.Logic.GameLoop" + ": endGame.");
        View.printlnMessage(MAGENTA_BOLD + "Это была отличная игра, " + player.getName() +
                "! Она войдет в анналы данной игровой истории!!!" + RESET);
        boolean flag = getAnswer(BrightYellow_BOLD + "Хотите сохранить игру? (1/0): " + RESET);
        if(flag) serializeLocation(player, locations);
        System.exit(0);
    }

}

