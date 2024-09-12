package homework2.task1;

import homework2.task1.models.Factory;
import homework2.task1.models.Scientist;


public class Main {

    public static void main(String[] args) throws InterruptedException {

        Factory factory = new Factory();
        Scientist firstScientist = new Scientist("First", factory, "[32m");
        Scientist secondScientist = new Scientist("Second", factory, "[36m");

        factory.start();
        firstScientist.start();
        secondScientist.start();
        factory.join();
        firstScientist.join();
        secondScientist.join();

        if (firstScientist.getResult() > secondScientist.getResult()) {
            System.out.println((char) 27 + "[33mFirst Scientist win with " + firstScientist.getResult()
                    + " robot(s)" + (char) 27 + "[0m"
            );
        } else if (firstScientist.getResult() < secondScientist.getResult()){
            System.out.println((char) 27 + "[33mSecond Scientist win with " + secondScientist.getResult()
                    + " robot(s)" + (char) 27 + "[0m"
            );
        } else {
            System.out.println((char) 27 + "[31mCompetition draw! " + (char) 27 + "[0m");
            System.out.println("Everybody created " + firstScientist.getResult() + " robot(s)");
        }
    }
}
