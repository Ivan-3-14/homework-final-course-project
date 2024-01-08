package task3.strategyOfstudents;

import task3.interfaces.Teach;

import static task3.constants.Constant.*;
import static task3.constants.Constant.TIME_COEF_OF_THIRD_TYPE_STUDENTS;

public class ThirdTypeStrategy implements Teach {

    @Override
    public String teachSkills(double talent) {
        String result;
        int type = TYPE_3;
        int hours = QUANTITY_OF_HOURS;
        double coeff = TIME_COEF_OF_THIRD_TYPE_STUDENTS;
        int subskills = COUNT_OF_SUBSKILLS_OF_THIRD_TYPE_STUDENTS;
        double generalNeededTime = hours * coeff / talent;
        double neededTime = generalNeededTime / subskills;
        return  result = ", type: " + type + ", talent: " + String.format("%.2f", talent) + " practice time: "
                + String.format("%.1f", neededTime) + "h, general time to study: "
                + String.format("%.1f",generalNeededTime) + " h";
    }
}
