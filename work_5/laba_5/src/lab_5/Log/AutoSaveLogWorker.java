package lab_5.Log;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class AutoSaveLogWorker implements Runnable {
    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try (FileWriter logWriter = new FileWriter("lab_5.Log.Log.txt");
                 BufferedWriter bufferedWriter = new BufferedWriter(logWriter)
            ) {
                ArrayList<String> logBuffer = Log.getLogHistory();
                for (int i = 0; i < logBuffer.size(); ++i) {
                    String s = logBuffer.get(i);
                    s += "\n";
                    bufferedWriter.write(s);
                }
                TimeUnit.SECONDS.sleep(5);
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

