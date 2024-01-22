package task1.fileWorker;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class FileWorker {

    public FileWorker() {}

    public ArrayList<String> readFile(File file) {
        ArrayList<String> listFromFile = new ArrayList<>();
        String line;
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream(file),
                        StandardCharsets.UTF_8)
        )) {
            while ((line = reader.readLine()) != null) {
                listFromFile.add(line.strip());
            }
            reader.close();
            if (listFromFile.size() == 0) {
                System.out.println("This file is empty");
            }
        } catch (FileNotFoundException er) {
            System.out.println("Please, check your file path ");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return listFromFile;
    }

    public void writeFile(File file, ArrayList<String> listToFile) {
        try (BufferedWriter writer = new BufferedWriter(
                new FileWriter(file)
        )) {
            for (var elements : listToFile) {
                writer.write(elements + '\n');
            }
        } catch (FileNotFoundException er) {
            System.out.println("Please, check your file path ");
        } catch (NullPointerException err) {
            System.out.println("result of your action is null ");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Done");
    }
}
