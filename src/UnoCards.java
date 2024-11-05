import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;


public abstract class UnoCards {
     ArrayList<Card> unoCards = new ArrayList<>();

    public UnoCards(){
        setCards();
        shuffleCards(unoCards);
    }

    public abstract void setCards();

    public static void shuffleCards(ArrayList<Card> cards){
         Collections.shuffle(cards);
    }

    public ArrayList<Card> getCards(){
        return unoCards;
    }

    public Card getTopCard(){
            Card top = unoCards.getLast();
            unoCards.removeLast();
            return top;

    }

    public int length(){
        return unoCards.size();
    }


    public void replace(ArrayList<Card> cards){
        unoCards = cards;
    }
}
