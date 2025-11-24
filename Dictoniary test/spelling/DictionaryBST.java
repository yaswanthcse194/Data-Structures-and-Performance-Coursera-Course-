package spelling;

import java.util.TreeSet;

public class DictionaryBST implements Dictionary {
    private TreeSet<String> words;

    public DictionaryBST() {
        words = new TreeSet<>();
    }

    @Override
    public boolean addWord(String word) {
        if (word == null) return false;
        return words.add(word.toLowerCase());
    }

    @Override
    public boolean isWord(String word) {
        if (word == null) return false;
        return words.contains(word.toLowerCase());
    }

    @Override
    public int size() {
        return words.size();
    }
}
