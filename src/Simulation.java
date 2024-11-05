public class Simulation {
    StandardVariation game;

    public Simulation(StandardVariation game){
        this.game = game;
    }

    public void reverse(){
        if (game.isReversOrder()){
            game.setCurrentPlayerIndex((game.getCurrentPlayerIndex() + game.getPlayersNum() + 1) % game.getPlayersNum()); ;
            game.setReversOrder(false);
        }
        else {
            game.setCurrentPlayerIndex((game.getCurrentPlayerIndex() + game.getPlayersNum() - 1) % game.getPlayersNum()); ;
            game.setReversOrder(true);
        }
    }

    public void skip(){
        if (game.isReversOrder()){
            game.setCurrentPlayerIndex((game.getCurrentPlayerIndex() - 1) % game.getPlayersNum()); ;
        }
        else {
            game.setCurrentPlayerIndex((game.getCurrentPlayerIndex() + 1) % game.getPlayersNum()); ;
        }
        if(game.getCurrentPlayerIndex() == -1){
            game.setCurrentPlayerIndex(game.getPlayersNum() - 1);
        }
    }

    public void drawTow(){
        for(int i = 0 ; i < 2 ; i ++){
            if(game.getDeckCards().length() == 1){
                game.getCurrentPlayer().addCard(game.deckCards.getTopCard());
                game.shufflePlayedCards();
            }
            game.getCurrentPlayer().addCard(game.deckCards.getTopCard());
        }
        System.out.println(game.getCurrentPlayer().getName() + " took tow cards from the deck cards");
        skip();
    }

    public String chooseBestColor(){
        String  [] colors = {"Blue" , "Red" , "Green" , "Yellow" };
        int r = 0,b = 0,g = 0,y = 0;
        for(Card card : game.getCurrentPlayer().getPlayerCards()){
            switch (card.getColor()) {
                case "Blue" -> b++;
                case "Red" -> r++;
                case "Green" -> g++;
                case "Yellow" -> y++;
            }
        }
        int max = Math.max(r,Math.max(b,Math.max(g,y)));
        if(max == r){
            System.out.println(game.getCurrentPlayer().getName() + " choose the Red color");
            return "Red";
        }
        if(max == g){
            System.out.println(game.getCurrentPlayer().getName() + " choose the Green color");
            return "Green";
        }
        if(max == b){
            System.out.println(game.getCurrentPlayer().getName() + " choose the Blue color");
            return "Blue";
        }
        System.out.println(game.getCurrentPlayer().getName() + " choose the Yellow color");
        return "Yellow";
    }

    public void wildDrawFour(){
        for(int i = 0 ; i < 4 ; i ++){
            if(game.getDeckCards().length() == 1){
                game.getCurrentPlayer().addCard(game.deckCards.getTopCard());
                game.shufflePlayedCards();
            }
            game.getCurrentPlayer().addCard(game.deckCards.getTopCard());
        }
        System.out.println(game.getCurrentPlayer().getName()+ " took four cards from the deck cards");
        skip();
    }

    public void simulate(){
        String winer = "";
        for (Player player : game.getPlayers()){
            if (player.playerCards.isEmpty()){
                winer = player.getName();
            }
        }
        if (!winer.isEmpty()){
            System.out.println("****************************************************************");
            System.out.println("****************** "+winer + " won the game "+"******************");
            return;
        }
        Card topcard = game.getPlayedCards().getLast();
        switch (topcard.getFunction()) {
            case "reverse" -> {
                reverse();
                game.playCard();
            }
            case "skip" -> {
                skip();
                game.playCard();
            }
            case "draw tow" -> {
                drawTow();
                game.playCard();
            }
            case "wild draw four" -> {
                wildDrawFour();
                game.playCard();
            }
            default -> {
                game.playCard();
            }
        }
        simulate();
    }

}
