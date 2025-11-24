package spelling;

import java.util.HashSet;
import java.util.Set;

/**
 * Dictionary implementation using a HashSet
 */
public class DictionaryHashSet implements Dictionary {
    private Set<String> words;
    
    public DictionaryHashSet() {
        words = new HashSet<String>();
    }
    
    public void addWord(String word) {
        if (word != null) {
            words.add(word.toLowerCase());
        }
    }
    
    @Override
    public boolean isWord(String word) {
        if (word == null) {
            return false;
        }
        return words.contains(word.toLowerCase());
    }
}

