package spelling;

import java.util.LinkedList;

public class DictionaryLL implements Dictionary {
    private LinkedList<String> words;

    public DictionaryLL() {
        words = new LinkedList<>();
    }

    @Override
    public boolean addWord(String word) {
        if (word == null) return false;
        word = word.toLowerCase();
        if (!words.contains(word)) {
            words.add(word);
            return true;
        }
        return false;
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
