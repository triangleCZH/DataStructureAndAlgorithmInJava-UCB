import java.util.*;

public class Trie {
    private HashMap<Character, TrieNode> myStartingLetters = new HashMap<>();
    ArrayList<TrieNode> collect = new ArrayList<>();

    public void insert(String word, long id) {
        // YOUR CODE HERE
        TrieNode node = new TrieNode();
        node.myNextLetters = this.myStartingLetters;
        insertHelper(word, id, node);
    }

    private void insertHelper(String word, long id, TrieNode node) {
        if (word.length() == 0) {
            node.id = id;
            node.isEnd = true;
        } else {
            if (node.myNextLetters.containsKey(word.charAt(0))) {
                insertHelper(word.substring(1), id, node.getMyNextLetters().get(word.charAt(0)));
            } else {
                TrieNode newNode=new TrieNode();
                node.myNextLetters.put(word.charAt(0),newNode);
                insertHelper(word.substring(1),id,node.getMyNextLetters().get(word.charAt(0)));
            }
        }
    }

    public ArrayList<Long> search(String word) {
        // YOUR CODE HERE
        TrieNode node = new TrieNode();
        ArrayList<Long> result = new ArrayList<>();
        node.myNextLetters=this.myStartingLetters;
        return searchHelper(word,node,result);
    }

    private ArrayList<Long> searchHelper(String word, TrieNode node, ArrayList<Long> list) {
        if (word.length() == 0) {
            if(node.isEnd){
                list.add(node.getId());
                return list;
            } else {// the case of fetching all of its children
                //fetchChild(node);
                return fetch(node, list);
            }
        } else {
            if (!node.myNextLetters.containsKey(word.charAt(0))) {
                return null;
            } else {
                return searchHelper(word.substring(1), node.myNextLetters.get(word.charAt(0)), list);
            }
        }
    }

    private ArrayList<Long> fetch(TrieNode node, ArrayList<Long> list){
        collect.clear();
        fetchHelper(node);
        for (TrieNode a: collect) {
            list.add(a.getId());
        }
        return list;
    }

    private void fetchHelper(TrieNode node) {
        if(node.isEnd){
            collect.add(node);
        } else {
            Set set = node.getMyNextLetters().keySet();
            Iterator<Character> iterator = set.iterator();
            while(iterator.hasNext()) {
                Character c = iterator.next();
                TrieNode child = node.getMyNextLetters().get(c);
                fetchHelper(child);
            }
        }
    }


    private class TrieNode {
        private HashMap<Character, TrieNode> myNextLetters = new HashMap<>();
        private long id;
        private boolean isEnd = false;
        //private String myDefinition;

        private TrieNode() {
        }

        public TrieNode(long id) {
            this.id = id;
        }

        public HashMap<Character, TrieNode> getMyNextLetters() {
            return myNextLetters;
        }

        public long getId() {
            return id;
        }
    }

    public static void main(String argv[]) {
        Trie trie = new Trie();
        trie.insert("ac", 123L);
        trie.insert("a c", 1234L);
        //trie.addDefinition("at", 1235L);
        List list = trie.search("a");
    }

}