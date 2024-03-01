import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Calculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        String[] parts = input.split(" ");
        if (parts.length != 3) {
            System.out.println("Output: throws Exception");
            return;
        }

        String first = parts[0];
        String operation = parts[1];
        String second = parts[2];

        boolean isRoman = isRomanNumeral(first) && isRomanNumeral(second);
        int num1 = isRoman ? romanToArabic(first) : Integer.parseInt(first);
        int num2 = isRoman ? romanToArabic(second) : Integer.parseInt(second);

        if (num1 < 1 || num1 > 10 || num2 < 1 || num2 > 10) {
            System.out.println("Output: throws Exception");
            return;
        }

        int result = 0;
        switch (operation) {
            case "+":
                result = num1 + num2;
                break;
            case "-":
                result = num1 - num2;
                break;
            case "*":
                result = num1 * num2;
                break;
            case "/":
                result = num1 / num2;
                break;
            default:
                System.out.println("Output: throws Exception");
                return;
        }

        if (isRoman) {
            if (result < 1) {
                System.out.println("Output: throws Exception");
                return;
            }
            System.out.println(arabicToRoman(result));
        } else {
            System.out.println(result);
        }
    }

    private static boolean isRomanNumeral(String input) {
        return input.matches("^[IVXLCDM]+$");
    }

    private static int romanToArabic(String input) {
        Map<Character, Integer> romanMap;
        romanMap = new HashMap<>();
        romanMap.put('I', 1);
        romanMap.put('V', 5);
        romanMap.put('X', 10);
        romanMap.put('L', 50);
        romanMap.put('C', 100);
        romanMap.put('D', 500);
        romanMap.put('M', 1000);

        int result = 0;
        int prevValue = 0;
        for (int i = input.length() - 1; i >= 0; i--) {
            int currentValue = romanMap.get(input.charAt(i));
            if (currentValue < prevValue) {
                result -= currentValue;
            } else {
                result += currentValue;
            }
            prevValue = currentValue;
        }
        return result;
    }

    private static String arabicToRoman(int input) {
        String[] romanNumerals = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        int[] values = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};

        StringBuilder result = new StringBuilder();
        int remainder = input;

        for (int i = 0; i < values.length; i++) {
            while (remainder >= values[i]) {
                result.append(romanNumerals[i]);
                remainder -= values[i];
            }
        }

        return result.toString();
    }
}