package CS146F19.Lin.project4;

import java.util.*;

public class SpellCheck {
    public List<String> spellCheck(RedBlackTree<String> dictionary, List<String> wordsInput) {
        List<String> wordsNotFound = new ArrayList<>();
        for (String x : wordsInput) {
            RedBlackTree.Node<String> node = dictionary.lookup(x);
            if (node == null) {
                wordsNotFound.add(x);
            }
        }
        return wordsNotFound;
    }

    public static void main(String[] args) {
        SpellCheck spellCheck = new SpellCheck();
        FileRead fileReader = new FileRead();
        long startDictionaryCreation = System.nanoTime();
        RedBlackTree<String> dictionary = fileReader.readDictionaryFile("dictonary/dictionary.txt");
        long endDictionaryCreation = System.nanoTime();
        System.out.println("Created dictionary in: " + (endDictionaryCreation - startDictionaryCreation) + " nanoseconds.");
        List<String> wordsInput1 = fileReader.readInput("sampleInputs/input1.txt");
        long startSpellCheck1 = System.nanoTime();
        List<String> wordsNotInDictionary1 = spellCheck.spellCheck(dictionary, wordsInput1);
        long endSpellCheck1 = System.nanoTime();
        System.out.println("Spell Checked Input in: " + (endSpellCheck1 - startSpellCheck1) + " nanoseconds.");
    }
}
