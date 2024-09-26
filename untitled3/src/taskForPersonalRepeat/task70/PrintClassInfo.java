package homework2.taskForPersonalRepeat.task70;


public class PrintClassInfo {
    public static void printInfoFromClass(Object o) {
        Class clas = o.getClass();
        System.out.println("Имя класса: " + clas);
        System.out.println("Поля класса: " + Arrays.toString(clas.getDeclaredFields()));
        System.out.println("Родительский класс: " + clas.getSuperclass());
        System.out.println("Методы класса: " +  Arrays.toString(clas.getDeclaredMethods()));
        System.out.println("Конструкторы класса: " + Arrays.toString(clas.getConstructors()));
    }
}
