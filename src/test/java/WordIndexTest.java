import org.junit.Before;
import org.junit.Rule;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.rules.TemporaryFolder;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.*;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class WordIndexTest {

    private WordIndex wordIndex;
    private File file;
    private Trie expectedTrie;

    @Rule
    TemporaryFolder folder = new TemporaryFolder();

     void setupTree() {
        expectedTrie = new Trie();
        expectedTrie.insert("Test", 1);
        expectedTrie.insert("load", 2);
        expectedTrie.insert("file", 3);
    }

    void setupFile(){
        try {
            folder.create();
            file = new File(folder.getRoot(), "Temp.txt");
            BufferedWriter bw = new BufferedWriter(new FileWriter(file));
            bw.write(" Test load,file ,.,.");
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void loadFile() {
        setupFile();
        setupTree();
        wordIndex = new WordIndex();
        wordIndex.loadFile(file.getAbsolutePath());
        Trie actualTrie = wordIndex.getFileData();

        assertEquals(expectedTrie, actualTrie);
    }

    @Test
    void loadAbsentFile() {
        wordIndex = new WordIndex();
        String nsf = "noSuchFile";
        assertDoesNotThrow(() -> wordIndex.loadFile(nsf));
    }

    @Test
    void getNoIndexes4Word() {
        wordIndex = new WordIndex();
        assertNull(wordIndex.getIndexes4Word("noSuchWord"));
    }

    @Test
    void getIndexes4Word(){
        setupFile();
        setupTree();

        wordIndex = new WordIndex();
        wordIndex.loadFile(file.getAbsolutePath());

        String searchWord = "load";
        Set<Integer> actualIndexes4Word = wordIndex.getIndexes4Word(searchWord);

        assertEquals(expectedTrie.getIndexes(searchWord), actualIndexes4Word);
    }
}