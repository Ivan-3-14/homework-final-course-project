package task1.textWorker;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringWorker {

    public StringWorker() {}

    public ArrayList<String> stringToArraySubstring(String string) {
        ArrayList<String> result = new ArrayList<>();
        Pattern pattern = Pattern.compile("\\w+\\t?");
        Matcher matcher = pattern.matcher(string);

        while (matcher.find()) {
            result.add(string.substring(matcher.start(), matcher.end()));
        }
        return result;
    }
}
