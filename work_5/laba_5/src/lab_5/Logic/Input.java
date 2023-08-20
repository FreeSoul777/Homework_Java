package lab_5.Logic;

import lab_5.Logic.View;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Input {

    public static String inputName() {
        View.printMessage("Введите ваше игровое имя: ");
        Scanner input = new Scanner(System.in);
        String name = input.nextLine();
        return name;
    }

    public static int inputAnswer() {
        while (true) {
            View.printMessage("Твой ответ: ");
            try {
                Scanner input = new Scanner(System.in);
                int number = input.nextInt();
                return number;
            } catch (InputMismatchException e) {
                View.printlnMessage(View.BrightYellow_BOLD + "Введено не число, попробуйте заново." + View.RESET);
            }
        }
    }
}
