package task1.textWorker;

import java.util.ArrayList;
import java.util.Comparator;

public class ComparatorByAlphabet implements Comparator<ArrayList<String>> {

    @Override
    public int compare(ArrayList<String> o1, ArrayList<String> o2) {
        int minArrayLength = Integer.min(o1.size(), o2.size());
        int result;
        for (int i = 0; i < minArrayLength; i++) {
            boolean element1IsNumber = isNumber(o1.get(i));
            boolean element2IsNumber = isNumber(o2.get(i));

            if (element1IsNumber && element2IsNumber) {
                result = Double.compare(Double.parseDouble(o1.get(i)), Double.parseDouble(o2.get(i)));
                if (result == 0) {
                    continue;
                } else {
                    return result;
                }
            }

            if (!element1IsNumber && !element2IsNumber) {
                result = o1.get(i).compareTo(o2.get(i));
                if (result == 0) {
                    continue;
                } else {
                    return result;
                }
            }

            if (element1IsNumber) {
                return -1;
            } else {
                return 1;
            }
        }
        return 0;
    }

    private boolean isNumber (String str) {
        try {
            Double.valueOf(str);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }
}
