package spelling;

public interface Dictionary {
    boolean addWord(String word);
    boolean isWord(String word);
    int size();
}
