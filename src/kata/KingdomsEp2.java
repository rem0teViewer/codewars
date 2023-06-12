package kata;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class KingdomsEp2 {

    public static String translate(String speech, String[] vocabulary) {
        String[] w = speech.split(" ");
        List<String> words = Arrays.stream(w)
                .map(s -> s.replaceAll("[^*a-zA-z]", ""))
                .collect(Collectors.toList());
        List<String> voc = Arrays.asList(vocabulary);
        ArrayList<String> variations = new ArrayList<>();
        int c = words.get(0).length() > 0 ? words.size() : 0;

        while (c > 0) {
            for (int i = 0; i < words.size(); i++) {
                for (int j = 0; j < voc.size(); j++) {
                    Matcher matcher = Pattern.compile(words.get(i).replaceAll("\\*", "\\\\w"))
                            .matcher(voc.get(j));
                    if (words.get(i).length() == voc.get(j).length() && matcher.matches()) {
                        variations.add(voc.get(j));
                    }
                }
                if (variations.size() == 1) {
                    words.set(i, w[i].replaceAll("[^.,?!]+", variations.get(0)));
                    voc.set(voc.indexOf(variations.get(0)), "");
                    c--;
                }
                variations.clear();
            }
        }
        return words.stream().collect(Collectors.joining(" "));
    }
}
