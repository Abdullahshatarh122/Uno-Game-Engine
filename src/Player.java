import java.util.ArrayList;

public class Player {
    String name;
    ArrayList<Card> playerCards = new ArrayList<Card>();

    public Player (String name)
    {
     this.name = name;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Card> getPlayerCards() {
        return playerCards;
    }

    public void addCard(Card card){
        playerCards.add(card);
    }

    public void drawCard(Card card){
        playerCards.remove(card);
    }

}
