package document;

import java.util.List;

public class EfficientDocument extends Document {

    private int numWords;
    private int numSentences;
    private int numSyllables;

    public EfficientDocument(String text) {
        super(text);
        processText();
    }

    private void processText() {
        List<String> tokens = getTokens("([a-zA-Z]+)|([!.?]+)");

        numWords = 0;
        numSentences = 0;
        numSyllables = 0;

        String lastToken = "";
        
        for (String token : tokens) {

            if (isWord(token)) {
                numWords++;
                numSyllables += countSyllables(token);
            } 
            else {
                numSentences++;
            }

            lastToken = token;
        }

        // Handle final sentence with no punctuation
        if (tokens.size() > 0 && isWord(lastToken)) {
            numSentences++;
        }
    }

    @Override
    public int getNumWords() {
        return numWords;
    }

    @Override
    public int getNumSentences() {
        return numSentences;
    }

    @Override
    public int getNumSyllables() {
        return numSyllables;
    }

    private boolean isWord(String s) {
        return s.matches("[a-zA-Z]+");
    }
}
