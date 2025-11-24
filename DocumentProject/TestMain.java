import document.*;

public class TestMain {
    public static void main(String[] args) {

        Document d = new BasicDocument("This is a test. How many sentences? Let's check!");

        System.out.println("Words: " + d.getNumWords());
        System.out.println("Sentences: " + d.getNumSentences());
        System.out.println("Syllables: " + d.getNumSyllables());
        System.out.println("Flesch Score: " + d.getFleschScore());
    }
}
