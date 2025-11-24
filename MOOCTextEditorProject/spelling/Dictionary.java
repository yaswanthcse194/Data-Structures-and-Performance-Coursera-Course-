package spelling;

/**
 * Interface for dictionary implementations
 */
public interface Dictionary {
    /**
     * Check if a word is in the dictionary
     * @param word The word to check
     * @return true if the word is in the dictionary, false otherwise
     */
    public boolean isWord(String word);
}

