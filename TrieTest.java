public class TrieTest{
  public static void main(String[] args){
    Trie trie = new Trie();
    trie.insert("bra");
    trie.insert("zra");
    trie.insert("bba");
    trie.insert("branz");
    System.out.println(trie.search("bra"));
    System.out.println(trie.search("branz"));
    System.out.println(trie.search("wayne"));
    System.out.println(trie.autoComplete("br"));
  }
}