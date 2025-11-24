package textgen;

public interface MarkovTextGenerator {
    void train(String sourceText);
    String generateText(int numWords);
    void retrain(String sourceText);
}
