import java.util.*;

public class Trie {
    private TrieNode root;

    private Long id;

    private ArrayList<Long> idList;
    public Trie() {
        root = new TrieNode();
        //idList = new ArrayList<Long>();
    }

    // Inserts a word into the trie.
    public void insert(String word, Long id) {
        HashMap<Character, TrieNode> children = root.children;

        for(int i=0; i<word.length(); i++){
            char c = word.charAt(i);

            TrieNode t;
            if(children.containsKey(c)){
                t = children.get(c);
                t.idList.add(id);
            }else{
                t = new TrieNode(c);
                t.idList.add(id);
                children.put(c, t);
            }

            children = t.children;

            //set leaf node
            if(i==word.length()-1)
                t.isLeaf = true;
                t.idList.add(id);
        }
    }

    // Returns if the word is in the trie.
    public HashSet<Long> search(String word) {
        TrieNode t = searchNode(word);
        return t.idList;

    }

    // Returns if there is any word in the trie
    // that starts with the given prefix.
    public boolean startsWith(String prefix) {
        if(searchNode(prefix) == null)
            return false;
        else
            return true;
    }

    public TrieNode searchNode(String str){
        ArrayList<Long> idList = new ArrayList<Long>();

        Map<Character, TrieNode> children = root.children;
        TrieNode t = null;
        for(int i=0; i<str.length(); i++){
            char c = str.charAt(i);
            if(children.containsKey(c)){
                t = children.get(c);
                children = t.children;
            }else{
                return null;
            }
        }



        return t;
    }

    private class TrieNode {
        char c;
        Long id;
        HashMap<Character, TrieNode> children = new HashMap<Character, TrieNode>();
        private HashSet<Long> idList;
        boolean isLeaf;

        public TrieNode() {
            this.idList = new HashSet<Long>();
        }

        public TrieNode(char c) {
            this.c = c;
            this.id = null;
            this.idList = new HashSet<Long>();
        }

        public TrieNode(char c, Long id) {
            this.c = c;
            this.idList = new HashSet<Long>();
        }
    }

        public static void main(String argv[]) {
        Trie trie = new Trie();
        trie.insert("abc", 12L);
        trie.insert("a c", 1234L);
            trie.insert("ab", 123L);
            trie.insert("ab c", 1235L);
        //trie.addDefinition("at", 1235L);
        System.out.println(trie.search("ab"));


    }
}