import java.nio.charset.CodingErrorAction;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

public abstract class Game{
    String variationName ;
    UnoCards deckCards ;
    int playersNum ;
    ArrayList<Player> players = new ArrayList<>();
    ArrayList<Card> playedCards = new ArrayList<>();
    boolean reversOrder;
    int currentPlayer;
    String currentColor;

    public Game(String variationName , int playersNum ){
        this.variationName = variationName;
        if (playersNum < 2){
            throw new IllegalArgumentException("Can't play a game with less that tow players!");
        }
        else if (playersNum > 10){
            throw new IllegalArgumentException("Can't play a game with more that ten players!");
        }
        else{
            this.playersNum = playersNum;
        }
        for (int i = 1 ; i <= playersNum ; i++){
            System.out.println("Enter the name of player number (" + i  + ")");
            Scanner scanner = new Scanner(System.in);
            players.add(new Player( scanner.nextLine()));
        }
        initCards();
        spreadCards();
        Card topCard = deckCards.getTopCard();
        while (topCard.color.equals("Wild")){ // we don't want the first card to be a wild card
            deckCards.getCards().add(new Random().nextInt(deckCards.getCards().size()) , topCard);
            topCard = deckCards.getTopCard();
        }
        playedCards.add(topCard);
        currentColor = topCard.getColor();
        reversOrder = false;
        currentPlayer = 0;

    }

    public Player getCurrentPlayer(){
        return players.get(currentPlayer);
    }

    public int getCurrentPlayerIndex() {
        return currentPlayer;
    }

    public boolean isReversOrder() {
        return reversOrder;
    }

    public int getPlayersNum() {
        return playersNum;
    }
    public void setCurrentPlayerIndex(int currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public void setReversOrder(boolean reversOrder) {
        this.reversOrder = reversOrder;
    }

    public void setPlayersNum(int playersNum) {
        this.playersNum = playersNum;
    }

    public UnoCards getDeckCards() {
        return deckCards;
    }
    public ArrayList<Card> getPlayedCards() {
        return playedCards;
    }
    public ArrayList<Player> getPlayers() {
        return players;
    }
    public String getCurrentColor() {
        return currentColor;
    }

    public void setCurrentColor(String currentColor) {
        this.currentColor = currentColor;
    }
    public abstract ArrayList<Card> getPlayableCards();

    public abstract void playCard();

    public abstract void initCards();

    public abstract void spreadCards();

    public abstract void play();

}