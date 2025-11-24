package spelling;

import java.util.HashMap;
import java.util.Set;

public class TrieNode {
    private HashMap<Character, TrieNode> children;
    private boolean isWord;
    private String text;  // the word up to this node

    public TrieNode() {
        children = new HashMap<>();
        isWord = false;
        text = "";
    }

    public TrieNode(String text) {
        this();
        this.text = text;
    }

    public TrieNode insert(char c) {
        if (!children.containsKey(c)) {
            TrieNode child = new TrieNode(text + c);
            children.put(c, child);
            return child;
        }
        return children.get(c);
    }

    public TrieNode getChild(char c) {
        return children.get(c);
    }

    public Set<Character> getValidNextCharacters() {
        return children.keySet();
    }

    public boolean endsWord() {
        return isWord;
    }

    public void setEndsWord(boolean isWord) {
        this.isWord = isWord;
    }

    public String getText() {
        return text;
    }
}
