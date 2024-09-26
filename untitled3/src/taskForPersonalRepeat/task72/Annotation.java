package homework2.taskForPersonalRepeat.task72;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class Annotation {
    @Retention(RetentionPolicy.RUNTIME)
    public  @interface AcademyInfo {
        String year();
    }

    @AcademyInfo (year =  "2023")
    public void checkWithAnnotation(){
        System.out.println("hello ");
    }

    public void checkWithoutAnnotation(){
        System.out.println("end ");
    }

}
