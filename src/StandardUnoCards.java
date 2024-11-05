import java.util.ArrayList;

public class StandardUnoCards extends UnoCards{

    public void setCards(){
        String  [] colors = {"Blue" , "Red" , "Green" , "Yellow" };

        for (String color : colors){
            unoCards.add(new Card(color , "0"));
            for (int i = 0 ; i < 2 ; i++){
                unoCards.add(new Card(color , "reverse"));
                unoCards.add(new Card(color , "skip"));
                unoCards.add(new Card(color , "draw tow"));
                for (int n = 1; n <= 9 ; n++){
                    unoCards.add(new Card(color , Integer.toString(n)));
                }
            }
        }
        for(int i = 0 ; i < 4 ; i++){
            unoCards.add(new Card("Wild" , "wild"));
            unoCards.add(new Card("Wild" , "wild draw four"));
        }
    }


}
