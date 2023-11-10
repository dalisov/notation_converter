public class Main {
    private static final Alphabet[] alphabet = Alphabet.values();
    private static final int FRACTION_LIMIT = 16;

    public static void main(String[] args) {
        //ввести изначальное число, его систему счисления (от двоичной до шестнадцатеричной) и искомую систему счисления
        convert("F.B", 16, 2);
    }

    private static void convert(String value, int notation, int scale) {
        int separator = value.indexOf('.');
        if (separator == -1)
            separator = value.length();
        int integer = 0;
        double decimal = 0.0;
        for (int i = 0; i < separator; i++) {
            int number = findNumber(value.charAt(i));
            integer += number * (int) Math.pow(notation, separator - 1 - i);
        }
        if (separator != value.length())
            for (int i = separator + 1; i < value.length(); i++) {
                int number = findNumber(value.charAt(i));
                decimal += number * Math.pow(notation, separator - i);
            }
        System.out.println(convertToScale(integer, decimal, scale));
    }

    private static int findNumber(char symbol) {
        int number;
        boolean character = false;
        for (Alphabet a : alphabet)
            if (symbol == a.getSymbol()) {
                character = true;
                break;
            }
        if (character)
            number = Alphabet.checkSymbol(symbol);
        else
            number = Character.getNumericValue(symbol);
        return number;
    }

    private static String convertToScale(int integer, double decimal, int scale) {
        StringBuilder converted = new StringBuilder();
        int zero = integer;
        do {
            int number = zero % scale;
            if (number > 9)
                converted.insert(0, Alphabet.checkNumber(number));
            else
                converted.insert(0, number);
            zero = zero / scale;
        } while (zero != 0);
        if (decimal != 0.0) {
            converted.append('.');
            double fraction = decimal;
            for (int i = 0; i < FRACTION_LIMIT; i++) {
                fraction *= scale;
                int number = (int) Math.floor(fraction);
                if (number > 9)
                    converted.append(Alphabet.checkNumber(number));
                else
                    converted.append(number);
                fraction %= 1;
                if (fraction == 0)
                    break;
            }
        }
        return converted.toString();
    }
}
