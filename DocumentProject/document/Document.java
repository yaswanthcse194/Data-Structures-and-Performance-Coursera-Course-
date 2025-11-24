package document;


import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class Document {
    
    private String text;
    
    protected Document(String text)
    {
        this.text = text;
    }
    
    protected String getText()
    {
        return this.text;
    }
    
    protected List<String> getTokens(String pattern)
    {
        Pattern tokSplitter = Pattern.compile(pattern);
        Matcher m = tokSplitter.matcher(text);
        java.util.ArrayList<String> tokens = new java.util.ArrayList<String>();
        
        while (m.find()) {
            tokens.add(m.group());
        }
        return tokens;
    }
    
    public abstract int getNumWords();
    public abstract int getNumSentences();
    public abstract int getNumSyllables();
    
    /**
     * Flesch Score calculation
     */
    public double getFleschScore()
    {
        double words = getNumWords();
        double sentences = getNumSentences();
        double syllables = getNumSyllables();
        
        if (words == 0 || sentences == 0) return 0.0;
        
        return 206.835 - 1.015 * (words / sentences) 
                       - 84.6 * (syllables / words);
    }
    
    /**
     * Count syllables in a word
     */
    protected static int countSyllables(String word)
    {
        int numSyllables = 0;
        boolean newSyllable = true;
        String vowels = "aeiouy";
        char[] cArray = word.toCharArray();
        
        for (int i = 0; i < cArray.length; i++)
        {
            char c = Character.toLowerCase(cArray[i]);
            
            if (i == cArray.length - 1 && c == 'e' && newSyllable && numSyllables > 0) {
                // ignore final "e"
                // do nothing (skip)
            }
            else if (vowels.indexOf(c) >= 0)
            {
                if (newSyllable) {
                    numSyllables++;
                    newSyllable = false;
                }
            }
            else {
                newSyllable = true;
            }
        }
        
        return numSyllables;
    }
}
