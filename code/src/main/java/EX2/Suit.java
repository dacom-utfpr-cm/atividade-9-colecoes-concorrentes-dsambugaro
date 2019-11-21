package EX2;
/*
 * @author Danilo Sambugaro created on 18/11/2019 inside the package - EX2
 */

import java.util.Random;



public enum Suit {
    CLUBS,
    HEARTS,
    DIAMONDS,
    SPADES;

    public static Suit getRandomSuit() {
        Random random = new Random();
        Suit value = null;
        while (value == null){
            value = values()[random.nextInt(values().length)];
        }
        return value;
    }
}
