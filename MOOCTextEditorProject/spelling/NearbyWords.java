package spelling;

import java.util.*;

/**
 * This class generates all nearby words for a given word using letter mutations.
 * It can be used for spelling suggestions.
 */
public class NearbyWords implements SpellingSuggest {
    // THRESHOLD to determine how many words to look through when stopping
    private static final int THRESHOLD = 1000;
    
    Dictionary dict;

    public NearbyWords(Dictionary dict) {
        this.dict = dict;
    }

    /**
     * Return the list of Strings that are one mutation away from the input String.
     * @param s The original String
     * @param wordsOnly If true, return only words that are in the dictionary
     * @return list of Strings which are nearby the original string
     */
    public List<String> distanceOne(String s, boolean wordsOnly) {
        List<String> retList = new ArrayList<String>();
        insertions(s, retList, wordsOnly);
        substitutions(s, retList, wordsOnly);
        deletions(s, retList, wordsOnly);
        return retList;
    }

    /**
     * Add to the currentList Strings that are one character mutation away
     * from the input string.
     * @param s The original String
     * @param currentList is the list of words to append modified words
     * @param wordsOnly If true, only add words that are in the dictionary
     */
    public void substitutions(String s, List<String> currentList, boolean wordsOnly) {
        // for each letter in the string
        for (int index = 0; index < s.length(); index++) {
            // for each letter in the alphabet
            for (int charCode = (int) 'a'; charCode <= (int) 'z'; charCode++) {
                // get the new string
                StringBuffer sb = new StringBuffer(s);
                sb.setCharAt(index, (char) charCode);

                // if the new string is not the original string
                // and if it's not already in the list
                String newString = sb.toString();
                if (!newString.equals(s) && !currentList.contains(newString)) {
                    // if wordsOnly is true, check if it's a word
                    if (!wordsOnly || dict.isWord(newString)) {
                        currentList.add(newString);
                    }
                }
            }
        }
    }

    /**
     * Add to the currentList Strings that are one character insertion away
     * from the input string.
     * @param s The original String
     * @param currentList is the list of words to append modified words
     * @param wordsOnly If true, only add words that are in the dictionary
     */
    public void insertions(String s, List<String> currentList, boolean wordsOnly) {
        // For each position in the string (including before first and after last)
        for (int index = 0; index <= s.length(); index++) {
            // For each letter in the alphabet
            for (int charCode = (int) 'a'; charCode <= (int) 'z'; charCode++) {
                // Create new string by inserting the letter at this position
                StringBuffer sb = new StringBuffer(s);
                sb.insert(index, (char) charCode);
                String newString = sb.toString();
                
                // If it's not already in the list
                if (!currentList.contains(newString)) {
                    // If wordsOnly is true, check if it's a word
                    if (!wordsOnly || dict.isWord(newString)) {
                        currentList.add(newString);
                    }
                }
            }
        }
    }

    /**
     * Add to the currentList Strings that are one character deletion away
     * from the input string.
     * @param s The original String
     * @param currentList is the list of words to append modified words
     * @param wordsOnly If true, only add words that are in the dictionary
     */
    public void deletions(String s, List<String> currentList, boolean wordsOnly) {
        // For each position in the string
        for (int index = 0; index < s.length(); index++) {
            // Create new string by removing the character at this position
            StringBuffer sb = new StringBuffer(s);
            sb.deleteCharAt(index);
            String newString = sb.toString();
            
            // If it's not already in the list
            if (!currentList.contains(newString)) {
                // If wordsOnly is true, check if it's a word
                if (!wordsOnly || dict.isWord(newString)) {
                    currentList.add(newString);
                }
            }
        }
    }

    /**
     * This method implements the suggestions method from the SpellingSuggest interface.
     * It uses a breadth-first search to find spelling suggestions.
     * 
     * @param word The misspelled word
     * @param numSuggestions The number of suggestions to return
     * @return List of spelling suggestions
     */
    @Override
    public List<String> suggestions(String word, int numSuggestions) {
        // Create a queue to hold words to explore
        Queue<String> queue = new LinkedList<String>();
        
        // Create a visited set to avoid looking at the same String repeatedly
        Set<String> visited = new HashSet<String>();
        
        // Create list of real words to return when finished
        List<String> retList = new LinkedList<String>();
        
        // Add the initial word to the queue and visited
        queue.add(word);
        visited.add(word);
        
        // Counter to track how many strings we've explored (for optimization)
        int exploredCount = 0;
        
        // while the queue has elements and we need more suggestions
        while (!queue.isEmpty() && retList.size() < numSuggestions && exploredCount < THRESHOLD) {
            // remove the word from the start of the queue and assign to curr
            String curr = queue.remove();
            exploredCount++;
            
            // get a list of neighbors (strings one mutation away from curr)
            List<String> neighbors = distanceOne(curr, false);
            
            // for each n in the list of neighbors
            for (String n : neighbors) {
                // if n is not visited
                if (!visited.contains(n)) {
                    // add n to the visited set
                    visited.add(n);
                    
                    // add n to the back of the queue
                    queue.add(n);
                    
                    // if n is a word in the dictionary
                    if (dict.isWord(n)) {
                        // add n to the list of words to return
                        retList.add(n);
                        
                        // If we have enough suggestions, we can break early
                        if (retList.size() >= numSuggestions) {
                            break;
                        }
                    }
                }
            }
        }
        
        // return the list of real words
        return retList;
    }

    public static void main(String[] args) {
        // basic testing code to get started
        String word = "tailo";
        // Pass NearbyWords any Dictionary implementation you prefer
        Dictionary d = new DictionaryHashSet();
        DictionaryLoader.loadDictionary(d, "data/dict.txt");
        NearbyWords w = new NearbyWords(d);
        List<String> l = w.distanceOne(word, true);
        System.out.println("One away word Strings for \"" + word + "\" are:");
        System.out.println(l + "\n");

        word = "tailo";
        List<String> suggest = w.suggestions(word, 10);
        System.out.println("Spelling Suggestions for \"" + word + "\" are:");
        System.out.println(suggest);
    }
}

