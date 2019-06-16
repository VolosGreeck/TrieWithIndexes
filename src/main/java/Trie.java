import java.util.Objects;
import java.util.Set;

public class Trie {
    private TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    public boolean insert(String word, int index) {
        if (word == null || index < 1) return false;

        TrieNode current = root;
        for (char ch : word.toCharArray()) {
            current = current.getChildren().computeIfAbsent(ch, node -> new TrieNode());
        }
        current.getIndex().add(index);
        current.setEndOfWord(true);
        return true;
    }

    private TrieNode getNode(String word) {
        if (word == null) return null;

        TrieNode current = root;

        for (char ch : word.toCharArray()) {
            TrieNode node = current.getChildren().get(ch);
            if (node == null) {
                return null;
            }
            current = node;
        }
        return current;
    }

    public boolean contains(String word) {
        TrieNode trieNode = getNode(word);
        if (trieNode == null) return false;
        return trieNode.isEndOfWord();
    }

    public Set<Integer> getIndexes(String word) {
        TrieNode trieNode = getNode(word);
        if (trieNode == null || trieNode.getIndex().isEmpty()) return null;
        return trieNode.getIndex();
    }

    public boolean isEmpty() {
        return root.getChildren().isEmpty();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Trie trie = (Trie) o;
        return Objects.equals(root, trie.root);
    }

    @Override
    public int hashCode() {

        return Objects.hash(root);
    }
}
