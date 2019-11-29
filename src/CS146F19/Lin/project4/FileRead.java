package CS146F19.Lin.project4;

import java.io.*;
import java.util.*;

public class FileRead {
    public RedBlackTree<String> readDictionaryFile(String fileName) {
        RedBlackTree<String> dictionary = new RedBlackTree<>();
        File dictionaryFile = new File(fileName);
        List<String> dictionaryData = readFile(dictionaryFile);
        for (String data : dictionaryData) {
            dictionary.insert(data);
        }
        return dictionary;
    }

    public List<String> readInput(String fileName) {
        List<String> words = new ArrayList<>();
        File inputFile = new File(fileName);
        List<String> inputData = readFile(inputFile);
        for (String data : inputData) {
            // If the line is empty "", skip it
            if (!data.isEmpty()) {
                // If the line has punctuation, replace it with "", lowercase everything, and split at spaces
                // "\\p{Punct}" allows the system to control removal of punctuation characters
                // "\\p{Blank}" allows the system to control removal of space and tab characters
                String[] wordArray = data.replaceAll("\\p{Punct}", "").toLowerCase().split("\\p{Blank}");
                words.addAll(Arrays.asList(wordArray));
            }
        }
        return words;
    }

    private List<String> readFile(File file) {
        List<String> stringArray = new ArrayList<>();
        BufferedReader br;
        try {
            br = new BufferedReader(new FileReader(file));
            String readLine = br.readLine();
            while (readLine != null) {
                stringArray.add(readLine);
                readLine = br.readLine();
            }
            br.close();
        } catch (FileNotFoundException e) {
            System.out.println("Oops. Cannot find file at expected location:");
            System.out.println(file.getAbsolutePath());
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Something happened while trying to read the file. Please try again.");
            e.printStackTrace();
        }
        return stringArray;
    }
}
