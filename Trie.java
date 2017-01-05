/**
 * @author Brandon Wayne Odiwuor
 */
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Map;
import java.io.FileReader;
import java.io.BufferedReader;

public class Trie {
  public static class TrieNode{
    boolean isWord;
    HashMap<Character, TrieNode> children = new HashMap<>(); 
    String word;
  }
  private TrieNode root = new TrieNode();
  
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
  
  public boolean search(String word){
    TrieNode currentNode = root;
    for(char character : word.toCharArray()){
      if(!currentNode.children.containsKey(new Character(character)))
        return false;
      currentNode = currentNode.children.get(new Character(character));
    }
    return currentNode.isWord;
  }
  
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
  
  public void traverseNodes(TrieNode currentNode, ArrayList<String> words){
    for(Map.Entry<Character, TrieNode> entry : currentNode.children.entrySet() ){
      if(entry.getValue().isWord == true)
        words.add(entry.getValue().word);
      traverseNodes(entry.getValue(), words);      
    }
  }  
  
  public void loadDictionary(){
    FileReader fileReader = null;
    BufferedReader bufferedReader = null;
    try{
      fileReader = new FileReader("dictionary.txt");
      bufferedReader = new BufferedReader(fileReader);
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