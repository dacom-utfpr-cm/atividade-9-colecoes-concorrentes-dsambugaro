package EX2;
/*
 *
 * @author Danilo Sambugaro created on 18/11/2019 inside the package - EX2
 *
 */

public class Card implements Comparable<Card> {

    private Character symbol;
    private Suit suit;
    private Integer value;

    public Card(Character symbol, Suit suit) {
        if (Character.isLetterOrDigit(symbol)) {
            if (Character.isAlphabetic(symbol)) {
                this.symbol = Character.toUpperCase(symbol);
            } else {
                this.symbol = symbol;
            }
        } else {
            new Exception("Invalid symbol");
        }

        if (this.symbol.equals(Character.toUpperCase('a'))) {
            this.value = 13;
        } else if (this.symbol.equals(Character.toUpperCase('j'))) {
            this.value = 3;
        } else if (this.symbol.equals(Character.toUpperCase('q'))) {
            this.value = 2;
        } else if (this.symbol.equals(Character.toUpperCase('k'))) {
            this.value = 1;
        } else {
            this.value = 0;
        }

        this.suit = suit;
    }

    public Card(Integer symbol, Suit suit) {

        if (symbol.compareTo(1) == 1 && symbol.compareTo(11) == -1) {
            if (symbol.equals(10)) {
                this.symbol = Character.forDigit(0, 10);
            } else {
                this.symbol = Character.forDigit(symbol, 10);
            }
        } else {
            new Exception("Invalid symbol");
        }

        if (symbol.equals(2)) {
            this.value = 12;
        } else if (symbol.equals(3)) {
            this.value = 11;
        } else if (symbol.equals(4)) {
            this.value = 10;
        } else if (symbol.equals(5)) {
            this.value = 9;
        } else if (symbol.equals(6)) {
            this.value = 8;
        } else if (symbol.equals(7)) {
            this.value = 7;
        } else if (symbol.equals(8)) {
            this.value = 6;
        } else if (symbol.equals(9)) {
            this.value = 5;
        } else if (symbol.equals(10)) {
            this.value = 4;
        } else {
            this.value = 0;
        }

        this.suit = suit;
    }

    @Override
    public int compareTo(Card card) {
        return value.compareTo(card.getValue());
    }

    public Character getSymbol() {
        return symbol;
    }

    public Suit getSuit() {
        return suit;
    }

    public Integer getValue() {
        return value;
    }

    public String toString() {
        String s = null;
        if (this.getSymbol().equals('0')) {
            s = "10 - " + this.getSuit().toString();
        } else {
            s = this.getSymbol() + " - " + this.getSuit().toString();
        }
        return s;
    }

}
