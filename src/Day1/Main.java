package Day1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    private static final Map<String, String> prefixMap = new HashMap<>();

    static {
        prefixMap.put("one", "1");
        prefixMap.put("two", "2");
        prefixMap.put("three", "3");
        prefixMap.put("four", "4");
        prefixMap.put("five", "5");
        prefixMap.put("six", "6");
        prefixMap.put("seven", "7");
        prefixMap.put("eight", "8");
        prefixMap.put("nine", "9");
    }

    public static int convertToNumber(String strNum) {
        try {
            return Integer.parseInt(strNum);
        } catch(NumberFormatException ex) {
            return 0;
        }
    }

    public static boolean isNumeric(String strNum) {
        try {
            int num = convertToNumber((strNum));
            return num != 0;
        } catch(NumberFormatException ex) {
            return false;
        }
    }

    public static String matchingPrefix(String word) {
        for(String key: prefixMap.keySet()) {
            if (word.startsWith(key)) {
                return prefixMap.get(key);
            }
        }
        return "";
    }

    public static void main(String[] args) {
        File file = new File("src/day1/inputs.txt");
        int sum = 0;

        try {
            Scanner sc = new Scanner(file);

            while (sc.hasNextLine()) {
                String word = sc.nextLine();

                ArrayList<String> digits = new ArrayList<>();

                for (int i = 0; i < word.length(); i++) {
                    String character = word.substring(i, i + 1);

                    if(isNumeric(character)) {
                        digits.add(character);
                    } else {
                        // check if the substring starting from this index matches one of the letter digits
                        String remainingWord = word.substring(i);
                        String matchingDigit = matchingPrefix(remainingWord);
                        if (!matchingDigit.isEmpty()) {
                            digits.add(matchingDigit);
                        }
                    }
                }

                String firstDigit = digits.get(0);
                String lastDigit = digits.get(digits.size() - 1);

                String calibration = firstDigit + lastDigit;

                sum += convertToNumber(calibration);
            }

            System.out.println(sum);
        } catch(FileNotFoundException ex) {
            System.out.println(ex);
        }

    }
}
