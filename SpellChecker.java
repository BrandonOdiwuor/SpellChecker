import java.util.ArrayList;
import java.io.FileReader;
import java.io.BufferedReader;

public class SpellChecker{
  Trie dictionary;
  
  /**
   * Constructor
   * @param fileName the name of the file containing words that are to be loaded into the trie
   */
  public SpellChecker(String fileName){
    dictionary = new Trie();
    dictionary.loadTrieFromFile(fileName);
  }
  
  /**
   * Spell checks a file with the help of spellCheckLine() function
   * @param fileName the name of the file that is to be spelt checked
   * @return an array list containing mispelled words in the file according 
   *               to the dictionary trie
   */
  public ArrayList<String> spellCheckFile(String fileName){
    ArrayList<String> mispelledWordsList = new ArrayList<>();
    try{
      BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));
      String line;
      while((line = bufferedReader.readLine()) != null){
        spellCheckLine(line, mispelledWordsList);
      }
      bufferedReader.close();
    }
    catch(Exception ex){
      ex.printStackTrace();
    }
    return mispelledWordsList;
  }
  
  /**
   * Checks for mispelled words in a line based on the dictionary Trie
   * @param the line being spelled checked
   * @param mispelledWords an array list where the mispelled words are to be added
   */
  private void spellCheckLine(String sentance, ArrayList<String> mispelledWords){
    String cleanWords = sentance.replaceAll("[(-+.^:,/)]","");
    String[] words;
    words = cleanWords.split("\\s");
    for(String word : words){
      if(word.length() > 0){
        if(!(dictionary.search(word.toLowerCase())))
          mispelledWords.add(word);
      }
    }
  }
}