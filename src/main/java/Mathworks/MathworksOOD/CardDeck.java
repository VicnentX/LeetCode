package Mathworks.MathworksOOD;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CardDeck {

    List<Integer> cardDeck;
    int curIndex;

    public void shuffle() {
        curIndex = 0;
        cardDeck = new ArrayList<>();
        for (int i = 1; i <= 52; ++i) {
            cardDeck.add(i);
        }
        Collections.shuffle(cardDeck);
    }

    public int getOneFromTop() {
        if (curIndex == 52) {
            System.out.println("there is no card left in card deck");
            return -1;
        }
        int curCard = cardDeck.get(curIndex);
        curIndex++;
        return curCard;
    }

    public int[] getMoreFromTop(int n) {
        if (curIndex + n - 1 >= 52) {
            System.out.println("there is only" + (52 - curIndex) + "card in the deck");
            return new int[] {-1};
        }
        int[] ret = new int[n];
        while (n > 0) {
            ret[ret.length - n] = cardDeck.get(curIndex);
            curIndex++;
            n--;
        }
        return ret;
    }

    public static void main(String[] args) {
        CardDeck cardDeck1 = new CardDeck();
        cardDeck1.shuffle();
        for (int i = 0 ; i < 53; ++i) {
            System.out.println("this is ith card from deck: " + cardDeck1.getOneFromTop());
        }
    }
}
