enum Alphabet {
    A(10, 'A'),
    B(11, 'B'),
    C(12, 'C'),
    D(13, 'D'),
    E(14, 'E'),
    F(15, 'F');
    private final int number;
    private final char symbol;

    Alphabet(int number, char symbol) {
        this.number = number;
        this.symbol = symbol;
    }

    int getNumber() {
        return number;
    }

    char getSymbol() {
        return symbol;
    }

    static int checkSymbol(char symbol) {
        for (int i = 0; i < Alphabet.values().length; i++) {
            if (Alphabet.values()[i].getSymbol() == symbol) {
                return Alphabet.values()[i].getNumber();
            }
        }
        return 0;
    }

    static char checkNumber(int number) {
        for (int i = 0; i < Alphabet.values().length; i++) {
            if (Alphabet.values()[i].getNumber() == number) {
                return Alphabet.values()[i].getSymbol();
            }
        }
        return 'x';
    }
}
