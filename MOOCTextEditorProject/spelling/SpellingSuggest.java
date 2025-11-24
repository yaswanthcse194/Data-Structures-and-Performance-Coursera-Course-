package spelling;

import java.util.List;

/**
 * Interface for spelling suggestion algorithms
 */
public interface SpellingSuggest {
    /**
     * Provide spelling suggestions for a given word
     * @param word The misspelled word
     * @param numSuggestions The number of suggestions to return
     * @return List of spelling suggestions
     */
    public List<String> suggestions(String word, int numSuggestions);
}

