import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class StringCalculator {

    public static int add(String s) {
        if (!s.isEmpty()) {
            List<Integer> numbers = arrayStringToListInteger(getSplit(s));
            negative(numbers);
            return numbers.stream().reduce(Integer::sum).orElseThrow(NumberFormatException::new);
        }
        return 0;
    }

    private static void negative(List<Integer> numbers) {
        StringBuilder sb = new StringBuilder();
        numbers.stream().filter(number -> number < 0).forEach(number -> sb.append(number).append(" "));
        if (!sb.toString().isEmpty()) {
            throw new RuntimeException("negatives not allowed " + sb);
        }
    }

    private static List<Integer> arrayStringToListInteger(String[] arrayStr) {
        return Arrays.stream(arrayStr).map(Integer::parseInt).collect(Collectors.toList());
    }

    private static String[] getSplit(String s) throws RuntimeException {
        if (s.startsWith("//")) {
            Matcher matcher = Pattern.compile("//(.)\n(.*)").matcher(s);
            if (matcher.matches()) {
                String delimiter = matcher.group(1);
                String toSplit = matcher.group(2);
                return toSplit.split(delimiter);
            }
            throw new RuntimeException("Delimiter invalid");
        }
        return s.split("[,\n]");
    }
}
