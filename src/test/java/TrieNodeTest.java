import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TrieNodeTest {

    private final TrieNode node = new TrieNode();

    @Test
    void getChildren() {
        assertTrue(node.getChildren().isEmpty());
    }

    @Test
    void getIndex() {
        assertTrue(node.getIndex().isEmpty());
    }

    @Test
    void isEndOfWord() {
        assertFalse(node.isEndOfWord());
    }

    @Test
    void setEndOfWord() {
        node.setEndOfWord(true);
        assertTrue(node.isEndOfWord());
    }
}