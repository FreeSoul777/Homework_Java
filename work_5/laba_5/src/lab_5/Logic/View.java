package lab_5.Logic;

public class View {
    public static final String RESET = "\033[0m";
    public static final String GREEN_BOLD = "\033[1;32m";
    public static final String RED_BOLD = "\033[1;31m";
    public static final String MAGENTA_BOLD = "\033[1;35m";
    public static final String BrightCyan_BOLD = "\033[1;96m";
    public static final String BrightYellow_BOLD = "\033[1;93m";


    public static void printlnMessage(String message) {
        System.out.println(message);
    }

    public static void printMessage(String message) {
        System.out.print(message);
    }
}
