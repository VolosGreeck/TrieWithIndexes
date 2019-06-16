import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.Set;

public class WordIndex {

    private final static Logger logger = LogManager.getLogger(WordIndex.class);

    private final static String REPLACE_REGEX = "[^А-яA-z0-9]";
    private final static String SPLIT_REGEX = "\\s+";

    private final Trie fileData = new Trie();

    public void loadFile(String fileName) {
        int index = 1;
        try (BufferedReader bf = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = bf.readLine()) != null) {
                for (String word : parseLine(line)) {
                    if (!"".equals(word)) {
                        logger.info(index);
                        fileData.insert(word, index);
                        index++;
                        logger.info(word);
                    }
                }
            }
            logger.info("Индексация завершена");
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }

    private String[] parseLine(String line) {
        return line.replaceAll(REPLACE_REGEX, " ").split(SPLIT_REGEX);
    }

    public Set<Integer> getIndexes4Word(String searchWord) {
        return fileData.getIndexes(searchWord);
    }

    public Trie getFileData() {
        return fileData;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Введите абсолютный путь к файлу:");
        String filePath;
        while (!new File(filePath = in.nextLine()).isFile()) {
            System.out.println("Файл не найден! Попробуйте еще раз.");
        }
        WordIndex wordIndex = new WordIndex();
        wordIndex.loadFile(filePath);
        System.out.println("Введите слово для поиска или \"Exit\" для выхода:");
        String line;
        while (!"Exit".equals(line = in.nextLine())) {
            System.out.println(wordIndex.getIndexes4Word(line));
        }
    }
}
