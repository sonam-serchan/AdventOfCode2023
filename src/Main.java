import com.sun.jdi.CharType;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // read the input file
        File file = new File("src/inputs.txt");

        try {
            // scan the file
            Scanner sc = new Scanner(file);

            // variable to store the sum of all calibration values
            int sum = 0;

            // loop over the file
            while(sc.hasNextLine()) {

                // check each word
                String word = sc.nextLine();

                // store first and last digit
                String firstDigit = "";
                String lastDigit = "";

                // loop over the word
                for (int i = 0; i < word.length(); i++) {
                    try {
                        // parse each character to integer
                        int parsedNumber = Integer.parseInt(String.valueOf(word.charAt(i)));

                        // assign to both firstDigit and lastDigit with the first digit found
                        if (firstDigit.isEmpty()) {
                            firstDigit = String.valueOf(parsedNumber);
                            lastDigit = String.valueOf(parsedNumber);
                        } else {
                            // if another digit found
                            lastDigit = String.valueOf(parsedNumber);
                        }
                    } catch (NumberFormatException exception) {
                        System.out.println("Exception");
                    }
                }
                // Store calibration of the word in the variable
                String calibration = firstDigit + lastDigit;

                // sum all calibrations
                sum += Integer.parseInt(calibration);
            }
            // print total sum of calibrations
            System.out.println("sum "+ sum);
        } catch (FileNotFoundException s) {
            System.out.println("Exception");
        }

    }
}