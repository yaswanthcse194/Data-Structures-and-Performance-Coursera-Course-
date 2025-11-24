package document;


import java.util.List;

public class BasicDocument extends Document {
    
    public BasicDocument(String text)
    {
        super(text);
    }

    /**
     * Return the number of words in the document.
     * Words are defined as contiguous alphabetic characters.
     */
    @Override
    public int getNumWords()
    {
        List<String> tokens = getTokens("[a-zA-Z]+");
        return tokens.size();
    }

    /**
     * Return the number of sentences in the document.
     * Sentences end with '.', '!', or '?'.
     * The last sequence of characters also counts as a sentence.
     */
    @Override
    public int getNumSentences()
    {
        List<String> tokens = getTokens("[^.!?]+");
        return tokens.size();
    }

    /**
     * Return the number of syllables in the document.
     * Uses countSyllables from the Document class.
     */
    @Override
    public int getNumSyllables()
    {
        List<String> words = getTokens("[a-zA-Z]+");
        int total = 0;
        
        for (String w : words) {
            total += countSyllables(w);
        }
        
        return total;
    }
}
