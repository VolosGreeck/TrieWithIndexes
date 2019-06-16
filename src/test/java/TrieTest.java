import org.junit.jupiter.api.Test;

import java.util.Set;
import java.util.TreeSet;

import static org.junit.jupiter.api.Assertions.*;

class TrieTest {

    private final Trie trie = new Trie();

    @Test
    void insert() {
        assertTrue(trie.insert("check", 1));
        assertFalse(trie.insert("zero", 0));
    }

    @Test
    void getIndexes() {
        Set<Integer> eq = new TreeSet<>();
        Set<Integer> notEq = new TreeSet<>();
        eq.add(1);
        notEq.add(2);

        trie.insert("check", 1);
        trie.insert("wrong", 1);
        assertEquals(eq, trie.getIndexes("check"));
        assertNotEquals(notEq, trie.getIndexes("wrong"));
    }
}