import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

public class Game {
    private ArrayList<String> titles = new ArrayList<>(); //List of movie titles
    private boolean won = true;
    private int points = 20;
    private String value = "";
    private HashSet<Character> guessedWords = new HashSet<>();
    private HashSet<Character> incorrectWords = new HashSet<>();

    //Constructor for Game class which reads the movie titles fromm a file and stores them in a list
    public Game(String path){
        File listOfMovies = new File(path);
        try {
            Scanner fileScanner = new Scanner(listOfMovies);
            while (fileScanner.hasNextLine())
                titles.add(fileScanner.nextLine());
        }
        catch (FileNotFoundException e){
            System.out.println("File not found");
        }
    }

    //Method selects a random movie title from the list
    public void setRandomTitle(){
        int index = (int)(Math.random()*titles.size())+1;
        value = titles.get(index-1);
    }

    //Method returns the random movie title selected
    public String getValue(){
        return value;
    }

    //Method prints out the guessed characters of the movie title
    public void displayTitle(){
        won = true;
        for(char c : value.toCharArray()) {
            if (c != 32 && guessedWords.contains(c)) {
                System.out.print(c);
            }
            else if(!Character.isLetter(c))
                System.out.print(c);
            else {
                won = false;
                System.out.print(" _");
            }
        }
    }

    //Method returns true or false depending on a correct guess of the character by user
    public boolean guess(){
        Scanner input = new Scanner(System.in);
        char letter = input.nextLine().charAt(0);
        addLetters(letter);
        won = true;
        if(getValue().indexOf(letter) == -1) {
            addIncorrect(letter);
            won = false;
            return false;
        }
        else
            return true;
    }
    public int getSizeIncorrect(){
        return incorrectWords.size();
    } //Returns number of incorrect guessed words
    public HashSet<Character> getIncorrectWords(){
        return incorrectWords;
    } //Returns all incorrect guessed words
    public void decreasePoints(){
        points--;
    }
    public int getPoints(){
        return points;
    }
    public void addLetters(char letter){
        guessedWords.add(letter);
    } //Adds guessed words to a hashset
    public void addIncorrect(char letter){
        incorrectWords.add(letter);
    } //Adds incorrect guessed words to a hashset
    public boolean gameWon(){
        return won;
    }
}
