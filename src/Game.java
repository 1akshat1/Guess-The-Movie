import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

public class Game {
    private ArrayList<String> titles = new ArrayList<>();
    private boolean won = true;
    private int points = 10;
    private String value = "";
    private HashSet<Character> guessedWords = new HashSet<>();
    private HashSet<Character> incorrectWords = new HashSet<>();

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
    public void setRandomTitle(){
        int index = (int)(Math.random()*titles.size())+1;
        value = titles.get(index);
    }
    public String getValue(){
        return value;
    }
    public void displayTitle(){
        for(char c : value.toCharArray()) {
            if (c != 32 && guessedWords.contains(c)) {
                System.out.print(c);
            } else {
                won = false;
                if (!Character.isLetter(c))
                    System.out.print(c);
                else
                    System.out.print(" _");
            }
        }
    }
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
    }
    public HashSet<Character> getIncorrectWords(){
        return incorrectWords;
    }
    public void decreasePoints(){
        points--;
    }
    public int getPoints(){
        return points;
    }
    public void addLetters(char letter){
        guessedWords.add(letter);
    }
    public void addIncorrect(char letter){
        incorrectWords.add(letter);
    }
    public boolean gameWon(){
        return won;
    }
}
