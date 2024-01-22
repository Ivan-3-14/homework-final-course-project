package task1.textWorker;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TextWorker {

    StringWorker stringWorker = new StringWorker();

    public TextWorker() {}

    public ArrayList<String> sortArray(ArrayList<String> list) {
        List<ArrayList<String>> listSorted;
        ArrayList<String> result = new ArrayList<>();
        ArrayList<ArrayList<String>> substring = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
             substring.add(stringWorker.stringToArraySubstring(list.get(i)));
        }
        listSorted = substring.stream().sorted(new ComparatorByAlphabet()).collect(Collectors.toList());
        for (int i = 0; i < listSorted.size(); i++) {
            result.add(listSorted.get(i).toString());
        }
        return result;
    }
}
