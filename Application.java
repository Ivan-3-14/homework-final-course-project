package task1;

import task1.fileWorker.FileWorker;
import task1.textWorker.TextWorker;

import java.io.*;
import java.util.ArrayList;

public class Application {
    public static void main(String[] args) {
        File inputFile = new File("C:\\Users\\user\\IdeaProjects\\jd2\\src\\task1\\files\\In");
        File outputFile = new File("C:\\Users\\user\\IdeaProjects\\jd2\\src\\task1\\files\\Out");
        FileWorker fileWorker = new FileWorker();
        TextWorker textWorker = new TextWorker();

        ArrayList<String> list = fileWorker.readFile(inputFile);
        ArrayList<String> result =  textWorker.sortArray(list);
        fileWorker.writeFile(outputFile, result);
    }
}
