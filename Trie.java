/**
 * @author Brandon Wayne Odiwuor
 */
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Map;
import java.io.FileReader;
import java.io.BufferedReader;

public class Trie {
  
  /**
   * ------------- nested Trie node class -------------
   * Stores information about the nodes in the Trie.
   */
  public static class TrieNode{
    public boolean isWord;
    public HashMap<Character, TrieNode> children = new HashMap<>(); 
    public String word;
  }
  // -------------- end of nested TrieNode class -------
  
  
  private TrieNode root = new TrieNode(); // Link to the root node
  
  /**
   * Adds a new word into a trie
   * @param word the word that is to be inserted into the Trie
   */
  public void insert(String word){
    TrieNode currentNode = root;
    for(char character : word.toCharArray()){
      if(!currentNode.children.containsKey(new Character(character)))
        currentNode.children.put(new Character(character), new TrieNode());
      currentNode = currentNode.children.get(new Character(character));
    }
    currentNode.isWord = true;
    currentNode.word = word;
  }
  
  /**
   * Looks up if a word exists in a trie
   * @param word the word being searched in the Trie
   * @return true word exists in the Trie, false otherwise
   */
  public boolean search(String word){
    TrieNode currentNode = root;
    for(char character : word.toCharArray()){
      if(!currentNode.children.containsKey(new Character(character)))
        return false;
      currentNode = currentNode.children.get(new Character(character));
    }
    return currentNode.isWord;
  }
  
  /**
   * Suggests a list of words that can be made out of the word provided
   * @param word the words whose possible competions are to be determined
   * @return an array list containing words that can be made out of the word 
   *               provided in the parameter, an empty array list if no word can be 
   *               out of the word provided
   */
  public ArrayList<String> autoComplete(String word){
    ArrayList<String> wordList = new ArrayList<>();
    TrieNode currentNode = root;
    for(char character : word.toCharArray()){
      currentNode = currentNode.children.get(new Character(character));
      if(currentNode == null)
        return wordList;
    }
    traverseNodes(currentNode, wordList);
    return wordList;
  }
  
  /**
   * Returns alternative spelling for a word not in the Trie
   * @param word the word not in the Trie
   * @return an array list containing words with similar spellings in the Trie
   */
  public ArrayList<String> suggestWords(String word){
    ArrayList<String> wordList = new ArrayList<>();
    TrieNode currentNode = root;
    for(char character : word.toCharArray()){
      if(currentNode == root && !currentNode.children.containsKey(new Character(character)))
        return wordList;
      if(currentNode.children.get(new Character(character)) == null){
        traverseNodes(currentNode, wordList);
        break;
      }
      else
        currentNode = currentNode.children.get(new Character(character));
    }
    return wordList;
  }
  
  /**
   * Traverses the Trie starting from currentNode and adds all the words found in the words array list
   * @param currentNode the point to start traversing
   * @param words the array list in which the words found during traversal are added
   */
  public void traverseNodes(TrieNode currentNode, ArrayList<String> words){
    for(Map.Entry<Character, TrieNode> entry : currentNode.children.entrySet() ){
      if(entry.getValue().isWord == true)
        words.add(entry.getValue().word);
      traverseNodes(entry.getValue(), words);      
    }
  }  
  
  /**
   * Reads words from the file whose file name is given into the Trie
   * @param fileName the file whose words are to be added into the Trie
   */
  public void loadTrieFromFile(String fileName){
    try{
      BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));
      String word;
      while((word = bufferedReader.readLine()) != null){
        insert(word);
      }
      bufferedReader.close();
    }
    catch(Exception ex){
      ex.printStackTrace();
    }
  }
  
}