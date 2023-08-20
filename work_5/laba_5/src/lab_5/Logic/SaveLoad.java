package lab_5.Logic;

import lab_5.Location;
import lab_5.Log.Log;
import lab_5.Logic.GameLoop;
import lab_5.Logic.View;
import lab_5.Player;

import java.io.*;
import java.util.ArrayList;

import static lab_5.Logic.View.*;

public class SaveLoad {

    private static final String fileName = "lab_5.Logic.SaveLoad.dat";

    public static void serializeLocation(Player player, ArrayList<Location> locations) {
        Log.writeInto("lab_5.Logic.SaveLoad: " + "serializeLocation()");
        View.printlnMessage(BrightYellow_BOLD + "Идет сохранение данных." + RESET);
        try (ObjectOutputStream oos =
                     new ObjectOutputStream(new FileOutputStream(fileName))) {

            oos.writeObject(player);
            oos.writeObject(locations);
            View.printlnMessage(GREEN_BOLD + "Успешно!" + RESET);

        } catch (Exception ex) {
            Log.writeInto("lab_5.Logic.SaveLoad: " + "serializeLocation(). ERROR" + ex.getMessage());
            View.printlnMessage(RED_BOLD + ex.getMessage() + RESET);
        }
    }

    public static boolean deserializeLocation() {
        Log.writeInto("lab_5.Logic.SaveLoad: " + "deserializeLocation()");
        View.printlnMessage(BrightYellow_BOLD + "Идет восстановление данных." + RESET);
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName)))
        {
            Player player = (Player) ois.readObject();
            ArrayList<Location> locations=((ArrayList<Location>)ois.readObject());
            View.printlnMessage(GREEN_BOLD + "Успешно!" + RESET);

            GameLoop.player = player;
            GameLoop.locations = locations;
        }
        catch (FileNotFoundException noFile) {
            Log.writeInto("lab_5.Logic.SaveLoad: " + "deserializeLocation(). File not find - " + fileName);
            View.printlnMessage(BrightYellow_BOLD + "Не обнаружено сохраненных данных." + RESET);
            return false;
        }
        catch(Exception ex){
            Log.writeInto("lab_5.Logic.SaveLoad: " + "serializeLocation(). ERROR" + ex.getMessage());
            View.printlnMessage(RED_BOLD + ex.getMessage() + RESET);
            return false;
        }

        return true;
    }
}
