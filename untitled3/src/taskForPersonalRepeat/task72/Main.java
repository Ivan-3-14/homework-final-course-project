package homework2.taskForPersonalRepeat.task72;

import java.lang.reflect.Method;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        checkMyAnnotationInMethod(new Annotation());
    }

    public static void checkMyAnnotationInMethod(Object o){
        Class clas = o.getClass();
        Method[] methods = clas.getDeclaredMethods();
        for (Method method : methods) {
            if (method.isAnnotationPresent(Annotation.AcademyInfo.class)) {
                System.out.println("method: " + method + " have annotation \"AcademyInfo\"");
            }
        }
    }
}
