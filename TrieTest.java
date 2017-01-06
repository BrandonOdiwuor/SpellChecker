/**
 * Test class for the Trie class
 * @author Brandon Wayne Odiwuor
 */
public class TrieTest{
  public static void main(String[] args){
    Trie trie = new Trie();
    
    // Testing insert() function
    trie.insert("purple");
    trie.insert("blue");
    trie.insert("black");
    trie.insert("blonde");
    trie.insert("red");
    trie.insert("brown");
    
    // Testing search() function
    testMethod(trie.search("purple"),  true);
    testMethod(trie.search("pink"),  false);
    testMethod(trie.search("red"),  true);
    
    // Testing autoComplete() function
    System.out.println(" Suggested list: " + trie.autoComplete("bl"));
    System.out.println(" Suggested list: " + trie.autoComplete("r"));
    System.out.println(" Suggested list: " + trie.autoComplete("b"));
    System.out.println(" Suggested list: " + trie.autoComplete(""));
    System.out.println(" Suggested list: " + trie.autoComplete("q"));
    
    // Testing suggestWords()
    System.out.println(" Suggested list: " + trie.suggestWords("bluq"));
     System.out.println(" Suggested list: " + trie.suggestWords("aluq")); 
  }
 
  /* 
   * The function takes a function's return value and compares it to what the function should to return
   * @param returnedValue the value returned by a funtion
   * @param expectedReturnValue the value that is to be returned by a function
   */
  public static void testMethod(boolean returnedValue, boolean expectedReturnValue){
    if(returnedValue == expectedReturnValue)
      System.out.println(" OK function returned: " + returnedValue + " expected: " + expectedReturnValue);
    else
      System.out.println(" X function returned: " + returnedValue + " expected: " + expectedReturnValue);
  }
}