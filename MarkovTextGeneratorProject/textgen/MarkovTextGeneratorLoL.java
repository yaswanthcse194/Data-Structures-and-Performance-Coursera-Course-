package textgen;

import java.util.*;

public class MarkovTextGeneratorLoL implements MarkovTextGenerator {

    private List<ListNode> wordList;  // list of nodes
    private String starter;           // first word in training text
    private Random rnGenerator;       // random generator

    // Constructor
    public MarkovTextGeneratorLoL(Random generator) {
        wordList = new LinkedList<>();
        starter = "";
        rnGenerator = generator;
    }

    // TRAIN method: builds the list of nodes from source text
    @Override
    public void train(String sourceText) {
        if (sourceText == null || sourceText.isEmpty()) return;

        String[] words = sourceText.split("\\s+"); // split by spaces
        starter = words[0];
        String prevWord = starter;

        for (int i = 1; i < words.length; i++) {
            String w = words[i];
            ListNode node = findNode(prevWord);
            if (node == null) {
                node = new ListNode(prevWord);
                wordList.add(node);
            }
            node.addNextWord(w);
            prevWord = w;
        }

        // link last word to starter to make it cyclic
        ListNode lastNode = findNode(prevWord);
        if (lastNode == null) {
            lastNode = new ListNode(prevWord);
            wordList.add(lastNode);
        }
        lastNode.addNextWord(starter);
    }

    // GENERATE TEXT method
    @Override
    public String generateText(int numWords) {
        if (wordList.isEmpty() || numWords == 0) return "";

        StringBuilder output = new StringBuilder();
        String currWord = starter;
        output.append(currWord);
        int count = 1;

        while (count < numWords) {
            ListNode node = findNode(currWord);
            if (node == null) break;

            String nextWord = node.getRandomNextWord(rnGenerator);
            output.append(" ").append(nextWord);
            currWord = nextWord;
            count++;
        }

        return output.toString();
    }

    // RETRAIN method: clears previous training and trains again
    @Override
    public void retrain(String sourceText) {
        wordList.clear();
        starter = "";
        train(sourceText);
    }

    // Helper method: find a node by word
    private ListNode findNode(String word) {
        for (ListNode node : wordList) {
            if (node.getWord().equals(word)) return node;
        }
        return null;
    }

    // toString for testing: prints the word list and their next words
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (ListNode node : wordList) {
            sb.append(node).append("\n");
        }
        return sb.toString();
    }
}

// LIST NODE class
class ListNode {

    private String word;            // the word this node represents
    private List<String> nextWords; // list of words that follow this word

    public ListNode(String word) {
        this.word = word;
        nextWords = new LinkedList<>();
    }

    public String getWord() {
        return word;
    }

    public void addNextWord(String nextWord) {
        nextWords.add(nextWord);
    }

    public String getRandomNextWord(Random generator) {
        if (nextWords.isEmpty()) return null;
        int index = generator.nextInt(nextWords.size());
        return nextWords.get(index);
    }

    @Override
    public String toString() {
        return word + ": " + String.join("->", nextWords) + "->";
    }
}
