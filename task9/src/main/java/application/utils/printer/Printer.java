package application.utils.printer;

public class Printer {

    private Printer() {
    }

    public static void printNotFoundMessage() {
        System.out.println("404 Not found");
    }

    public static void printSuccessfulSaveMethodMessage() {
        System.out.println(" save successful ");
    }

    public static void printSuccessfulDeleteMethodMessage() {
        System.out.println("delete successful");
    }

    public static void printSuccessfulUpdateMethodMessage() {
        System.out.println("update successful");
    }

    public static void printNullPointerMessage() {
        System.out.println("this is null");
    }

}
