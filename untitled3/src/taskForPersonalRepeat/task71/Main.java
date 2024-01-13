package homework2.taskForPersonalRepeat.task71;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Main {
    public static void main(String[] args)  {
        invokePrintHelloWorldMethod();
    }

    public static void invokePrintHelloWorldMethod() {
        Class clas;
        MyClass myClass;

        {
            try {
                myClass = new MyClass();
                clas = Class.forName(MyClass.class.getName());
                Method method = clas.getDeclaredMethod("printHelloWorld");
                method.setAccessible(true);
                method.invoke(myClass);

            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException |
                     ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}
