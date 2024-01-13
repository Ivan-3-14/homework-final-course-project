package strategyOfstudents;

import interfaces.Teach;

import static constants.Constant.*;

public class SecondTypeStrategy implements Teach {

    @Override
    public String teachSkills(double talent) {
        String result;
        int type = TYPE_2;
        int hours = QUANTITY_OF_HOURS;
        double coeff = TIME_COEF_OF_SECOND_TYPE_STUDENTS;
        int subskills = COUNT_OF_SUBSKILLS_OF_SECOND_TYPE_STUDENTS;
        double generalNeededTime = hours * coeff / talent;
        double neededTime = generalNeededTime / subskills;
        return  result = ", type: " + type + ", talent: " + String.format("%.2f", talent) + " parsing time: "
                + String.format("%.1f", neededTime) + "h, practice time: " + String.format("%.1f", neededTime)
                + "h, general time to study: " + String.format("%.1f",generalNeededTime) + " h";
    }
}
