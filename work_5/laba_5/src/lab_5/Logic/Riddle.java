package lab_5.Logic;

import lab_5.Log.Log;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Riddle implements Serializable {

    private String riddle;
    private String trueAnswer;
    private String[] falseAnswers;
    private int complexity;
    private int trueNumber;

    public Riddle(int complexity, String riddle, String trueAnswer, String[] falseAnswers) {
        this.complexity = complexity;
        this.riddle = riddle;
        this.trueAnswer = trueAnswer;
        this.falseAnswers = falseAnswers;
    }

    public int getTrueNumber() {
        return trueNumber;
    }

    public int getComplexity() {
        return complexity;
    }

    public void setComplexity(int complexity) {
        this.complexity = complexity;
    }

    public String getRiddle() {
        return riddle;
    }

    public void setRiddle(String riddle) {
        this.riddle = riddle;
    }

    public String getTrueAnswer() {
        return trueAnswer;
    }

    public void setTrueAnswer(String trueAnswer) {
        this.trueAnswer = trueAnswer;
    }

    public String[] getFalseAnswers() {
        return falseAnswers;
    }

    public void setFalseAnswers(String[] falseAnswers) {
        this.falseAnswers = falseAnswers;
    }

    public String getAllAnswers() {
        Log.writeInto("lab_5.Logic.Riddle: " + "getAllAnswers()");
        List<String> words = new ArrayList<>();
        words.add(trueAnswer);
        words.addAll(Arrays.stream(falseAnswers).toList());
        Collections.shuffle(words);
        for(int i = 0; i < 3; ++i) {
            if(words.get(i).equals(trueAnswer))
                trueNumber = i + 1;
        }
        String s = "1) " + words.get(0) + " 2) " + words.get(1) + " 3) " + words.get(2);
        return s;
    }

    @Override
    public String toString() {
        return new StringBuffer("\nlab_5.Logic.Riddle : ").append(this.riddle)
                .append("\nComplexity : ").append(this.complexity)
                .append("\nTrue Answer : ").append(this.trueAnswer)
                .append("\nFalse Answers : ").append(this.falseAnswers[0]).append(", ")
                .append(this.falseAnswers[1]).toString();
    }
}

