import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        System.out.println(calc(input));
    }

    public static String calc(String input) {

        String[] tokens = input.split(" ");
        int a;
        int b;

        try {
            a = getNumber(tokens[0]);
            b = getNumber(tokens[2]);
        } catch (NumberFormatException e) {
            throw  new IllegalArgumentException("Invalid input format");
        }

        if (a < 1 || a > 10 || b < 1 || b > 10) {
            throw new IllegalArgumentException("Number out of range");
        }

        int result = switch (tokens[1]) {
            case "+" -> a + b;
            case "-" -> a - b;
            case "*" -> a * b;
            case "/" -> a / b;
            default -> throw new IllegalArgumentException("Invalid operator");
        };

        if (isRoman(tokens[0]) && isRoman(tokens[2])) {
            if (result <= 0) {
                throw new IllegalArgumentException("Roman numerals can not be negative or zero");
            }
            return toRoman(result);
        }

        return String.valueOf(result);
    }

    private static boolean isRoman(String s) {
        return s.equals("I") || s.equals("II") || s.equals("III") || s.equals("IV") ||
                s.equals("V") || s.equals("VI") || s.equals("VII") || s.equals("VIII") ||
                s.equals("IX") || s.equals("X");
    }

    private static int getNumber(String s) {
        if (isRoman(s)) {
            return fromRoman(s);
        } else {
            return Integer.parseInt(s);
        }
    }

    private static String toRoman(int number) {
        if (number < 1 || number > 3999) {
            throw new IllegalArgumentException("Roman numerals can only represent integers between 1 and 3999");
        }
        String[] thousands = {"", "M", "MM", "MMM"};
        String[] hundreds = {"", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"};
        String[] tens = {"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"};
        String[] ones = {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"};
        return thousands[number/1000] + hundreds[(number%1000)/100] +
                tens[(number%100)/10] + ones[number%10];
    }

    private static int fromRoman(String s) {
        return switch (s) {
            case "I" -> 1;
            case "II" -> 2;
            case "III" -> 3;
            case "IV" -> 4;
            case "V" -> 5;
            case "VI" -> 6;
            case "VII" -> 7;
            case "VIII" -> 8;
            case "IX" -> 9;
            case "X" -> 10;
            default -> throw new IllegalArgumentException("Invalid Roman numeral");
        };
    }
}