package lab_5.Log;

import java.util.ArrayList;

public class Log {

    private static ArrayList<String> logHistory = new ArrayList<>();

    public static void writeInto(String message){
        logHistory.add(message);
    }

    public static ArrayList<String> getLogHistory(){
        return logHistory;
    }

}
