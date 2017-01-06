import java.util.ArrayList;

public class MainProgram{
  public static void main(String[] args){
    
    SpellChecker spellChecker = new SpellChecker("dictionary.txt");
    
    ArrayList<String> mispelledWords = spellChecker.spellCheckFile("words3.txt");
    
    System.out.println(mispelledWords);
  }
}