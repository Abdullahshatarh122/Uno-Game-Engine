public class Card {
    String color;
    String function;

    public Card (String color , String function){
        this.color = color;
        this.function = function;
    }

    public String getColor() {
        return color;
    }

    public String getFunction() {
        return function;
    }

    @Override
    public String toString() {
        return color + " " + '\'' + function + '\'';
    }
}
