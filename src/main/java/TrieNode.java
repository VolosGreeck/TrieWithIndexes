import java.util.*;

public class TrieNode {

    private final Map<Character, TrieNode> children = new HashMap<>();
    private Set<Integer> index = new TreeSet<>();
    private boolean endOfWord;

    Map<Character, TrieNode> getChildren() {
        return children;
    }

    public Set<Integer> getIndex() {
        return index;
    }

    boolean isEndOfWord() {
        return endOfWord;
    }

    void setEndOfWord(boolean endOfWord) {
        this.endOfWord = endOfWord;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TrieNode trieNode = (TrieNode) o;
        return Objects.equals(children, trieNode.children) &&
                Objects.equals(index, trieNode.index);
    }

    @Override
    public int hashCode() {

        return Objects.hash(children, index);
    }
}
