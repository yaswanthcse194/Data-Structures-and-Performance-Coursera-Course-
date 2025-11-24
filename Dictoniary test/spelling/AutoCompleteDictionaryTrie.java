package spelling;

import java.util.*;

public class AutoCompleteDictionaryTrie implements AutoComplete, Dictionary {
    private TrieNode root;
    private int size;

    public AutoCompleteDictionaryTrie() {
        root = new TrieNode();
        size = 0;
    }

    @Override
    public boolean addWord(String word) {
        if (word == null || word.isEmpty()) return false;

        word = word.toLowerCase();
        TrieNode current = root;
        boolean isNewWord = false;

        for (char c : word.toCharArray()) {
            TrieNode child = current.getChild(c);
            if (child == null) {
                child = current.insert(c);
                isNewWord = true;
            }
            current = child;
        }

        if (!current.endsWord()) {
            current.setEndsWord(true);
            size++;
            return true;
        }

        return false; // word already existed
    }

    @Override
    public boolean isWord(String word) {
        if (word == null || word.isEmpty()) return false;

        word = word.toLowerCase();
        TrieNode current = root;

        for (char c : word.toCharArray()) {
            current = current.getChild(c);
            if (current == null) return false;
        }

        return current.endsWord();
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public List<String> predictCompletions(String prefix, int numCompletions) {
        List<String> completions = new LinkedList<>();
        if (prefix == null || numCompletions == 0) return completions;

        prefix = prefix.toLowerCase();
        TrieNode current = root;

        // find the node corresponding to the last char of prefix
        for (char c : prefix.toCharArray()) {
            current = current.getChild(c);
            if (current == null) return completions; // no completions
        }

        // BFS for autocomplete
        Queue<TrieNode> queue = new LinkedList<>();
        queue.add(current);

        while (!queue.isEmpty() && completions.size() < numCompletions) {
            TrieNode node = queue.remove();
            if (node.endsWord()) completions.add(node.getText());

            for (Character c : node.getValidNextCharacters()) {
                queue.add(node.getChild(c));
            }
        }

        return completions;
    }
}
