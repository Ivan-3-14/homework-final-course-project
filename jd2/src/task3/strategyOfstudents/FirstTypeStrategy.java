package task3.strategyOfstudents;

import task3.interfaces.Talent;

import static task3.constants.Constant.TALENT_OF_FIRST_TYPE_STUDENTS;

public class FirstTypeStrategy implements Talent {

    @Override
    public void teachSkills(int hours, double talent, int subskills) {
        double generalNeededTime = hours / talent;
        double neededTime = generalNeededTime / subskills;
        System.out.println("Type talent: " + talent + " parsing time: "
         + neededTime + " practice time: " + neededTime + " stream time: " + neededTime + " general time to study: "
                + generalNeededTime
        );
    }
}
