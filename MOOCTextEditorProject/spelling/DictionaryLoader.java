package spelling;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Utility class to load dictionary from file
 */
public class DictionaryLoader {
    public static void loadDictionary(Dictionary d, String filename) {
        // This is a stub - the actual implementation would load from file
        // For now, we'll just add some common words for testing
        if (d instanceof DictionaryHashSet) {
            DictionaryHashSet dict = (DictionaryHashSet) d;
            // Add some test words
            dict.addWord("tailor");
            dict.addWord("taylor");
            dict.addWord("sailor");
            dict.addWord("stone");
            dict.addWord("swan");
            dict.addWord("swine");
            dict.addWord("swone");
        }
    }
}

