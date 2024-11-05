import java.util.ArrayList;
import java.util.Random;

public class StandardVariation extends Game{
    Simulation simulator;

    public StandardVariation(String variationName , int playersNum) {
        super(variationName, playersNum);
        simulator = new Simulation(this);
    }

    @Override
    public void initCards() {
        this.deckCards = new StandardUnoCards();
    }

    @Override
    public void spreadCards() {
        for (int i = 0 ; i < 7 ; i ++){
            for (Player player : players){
                player.addCard(deckCards.getTopCard());
            }
        }
    }
    @Override
    public ArrayList<Card> getPlayableCards(){
        Player player = getCurrentPlayer();
        ArrayList<Card> playableCards = new ArrayList<Card>();
        for (Card card : player.getPlayerCards()){
            if(card.getColor().equals(getCurrentColor()) || card.getFunction().equals(playedCards.getLast().getFunction()) || card.getColor().equals("Wild")){
                playableCards.add(card);
            }
        }
        return playableCards;
    }

    @Override
    public void playCard(){
        Player player = getCurrentPlayer();
        ArrayList<Card> playableCards = getPlayableCards();

        if(playableCards.isEmpty()){
            if(deckCards.length() == 1){
                player.addCard(deckCards.getTopCard());
                shufflePlayedCards();
            }
            else {
                player.addCard(deckCards.getTopCard());
            }
            System.out.println(getCurrentPlayer().getName() + " : took one card from the deck cards");
            playableCards = getPlayableCards();
        }
        if(!playableCards.isEmpty()){
            Card card = playableCards.get(new Random().nextInt(playableCards.size()));
            setCurrentColor(card.getColor());
            player.drawCard(card);
            playedCards.addLast(card);
            System.out.println(player.getName() + " played : " + card.toString());
            if(card.getColor().equals("Wild")){
               setCurrentColor(simulator.chooseBestColor());
            }
            if(player.getPlayerCards().size() == 1 ){
                System.out.println(player.getName() + " : UNO");
            }
        }
        if(!playedCards.getLast().getFunction().equals("reverse"))
            simulator.skip();

    }

    public void shufflePlayedCards(){
        Card currentCard = playedCards.getLast();
        playedCards.removeLast();
        UnoCards.shuffleCards(playedCards);
        deckCards.replace(playedCards);
        playedCards.clear();
        playedCards.add(currentCard);
        System.out.println("Deck cards is empty --> played cards shuffled");
    }

    @Override
    public void play() {
        System.out.println("************** Welcome to " + variationName + " game ****************");
        Card firstCard = playedCards.getLast();
        System.out.println("first card is : " +firstCard.toString());
        if (firstCard.getFunction().equals("reverse")){
            simulator.reverse();
        }
        else if (firstCard.getFunction().equals("skip")){
            simulator.skip();
        }
        else if (firstCard.getFunction().equals("draw tow")){
            simulator.drawTow();
        }
            playCard();
            simulator.simulate();

    }


}
