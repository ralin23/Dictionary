package CS146F19.Lin.project4;

import java.util.*;

public class SpellCheck {
    /**
     * Checks words in a supplied dictionary
     *
     * @param dictionary the dictionary to use
     * @param wordsInput an array of words to check against the dictionary
     * @return an array of words not found in the dictionary
     */
    public List<String> spellCheck(RedBlackTree<String> dictionary, List<String> wordsInput) {
        List<String> wordsNotFound = new ArrayList<>();
        long startSpellCheck1 = System.nanoTime();
        for (String x : wordsInput) {
            Node<String> node = dictionary.lookup(x);
            if (node == null) {
                wordsNotFound.add(x);
            }
        }
        long endSpellCheck1 = System.nanoTime();
        System.out.println("Spell Checked Input in: " + (endSpellCheck1 - startSpellCheck1) + " nanoseconds.");
        return wordsNotFound;
    }

    public static void main(String[] args) {
        SpellCheck spellCheck = new SpellCheck();
        FileRead fileReader = new FileRead();
        RedBlackTree<String> dictionary = fileReader.readDictionaryFile("dictionary/dictionary.txt");
        List<String> wordsInput1 = fileReader.readInput("sampleInputs/input1.txt");
        List<String> wordsNotInDictionary1 = spellCheck.spellCheck(dictionary, wordsInput1);
        System.out.println("Words not in the dictionary (includes repeats): ");
        for (String x : wordsNotInDictionary1) {
            System.out.println(x);
        }
    }
}
