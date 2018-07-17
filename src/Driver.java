
public class Driver {

    public static void main (String [] args) {


        Game play = new Game("movies1.txt"); //Create a new Game object
        play.setRandomTitle();
        System.out.println("You are guessing: ");
        play.displayTitle();

        //User input
        while(play.getPoints()>0){
            System.out.println("Guesses left:" + play.getPoints());
            System.out.println("Your guess: ");
            if(!play.guess())
               System.out.println("Try Again");
            System.out.println("You are guessing: ");
            play.displayTitle();
            System.out.println();
            System.out.println("You have guessed ("+play.getSizeIncorrect()+") wrong letters: "+ play.getIncorrectWords());
            System.out.println();
            if(play.gameWon()) {
                System.out.println("Won!");
                break;
            }
            play.decreasePoints();
        }
        if(!play.gameWon())
            System.out.println("LOST");
    }
}
