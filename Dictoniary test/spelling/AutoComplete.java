package spelling;

import java.util.List;

public interface AutoComplete {
    List<String> predictCompletions(String prefix, int numCompletions);
}
