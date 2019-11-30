package CS146F19.Lin.project4;

import static org.junit.Assert.*;

import org.junit.Test;

import java.util.*;


public class RBTTester {

    @Test
    //Test the Red Black Tree
    public void test() {
        RedBlackTree<String> rbt = new RedBlackTree<>();
        rbt.insert("D");
        rbt.insert("B");
        rbt.insert("A");
        rbt.insert("C");
        rbt.insert("F");
        rbt.insert("E");
        rbt.insert("H");
        rbt.insert("G");
        rbt.insert("I");
        rbt.insert("J");
        assertEquals("DBACFEHGIJ", makeString(rbt));
        String str = "Color: 1, Key:D Parent: \n" +
                "Color: 1, Key:B Parent: D\n" +
                "Color: 1, Key:A Parent: B\n" +
                "Color: 1, Key:C Parent: B\n" +
                "Color: 1, Key:F Parent: D\n" +
                "Color: 1, Key:E Parent: F\n" +
                "Color: 0, Key:H Parent: F\n" +
                "Color: 1, Key:G Parent: H\n" +
                "Color: 1, Key:I Parent: H\n" +
                "Color: 0, Key:J Parent: I\n";
        assertEquals(str, makeStringDetails(rbt));
    }

    @Test
    //Test the spell check
    public void spellCheckTest() {
        FileRead fileReader = new FileRead();
        RedBlackTree<String> dictionary = fileReader.readDictionaryFile("dictonary/dictionary.txt");
        SpellCheck spellCheck = new SpellCheck();
        List<String> wordsInput1 = new ArrayList<>();
        wordsInput1.add("hello");
        wordsInput1.add("hellishly");
        wordsInput1.add("zzzzzzzzzzz");
        wordsInput1.add("zywiel");
        List<String> wordsNotFound1 = spellCheck.spellCheck(dictionary, wordsInput1);
        List<String> wordsNotFoundExpected1 = new ArrayList<>();
        wordsNotFoundExpected1.add("zzzzzzzzzzz");
        assertEquals(wordsNotFoundExpected1, wordsNotFound1);

        List<String> wordsInput2 = fileReader.readInput("sampleInputs/input1.txt");
        List<String> wordsNotFound2 = spellCheck.spellCheck(dictionary, wordsInput2);
        String[] wordsNotFoundExpected2 = {
                "now", "i", "can’t", "get", "the", "who’s", "for", "me", "by", "the", "in",
                "or", "the", "be", "the", "in", "selforgans", "i", "ask", "as", "me", "it’s",
                "for", "a", "as", "if", "i’m", "not", "the", "one", "on", "don’t", "of",
                "as", "you", "eye", "me", "the", "at", "i", "to", "the", "as", "of", "as",
                "the", "now", "docile", "and", "so", "too", "for", "the", "quasi", "a", "hair’s",
                "we", "had", "and", "my", "we", "be", "of", "i", "to", "of", "my", "out",
                "and", "in", "everyone’s", "to", "her", "own", "of", "there’s", "the", "gettoknowyou",
                "we", "no", "for", "we", "and", "knowyou", "if", "you", "had", "to", "the",
                "in", "you", "of", "a", "halffrozen", "by", "the", "i", "my", "i", "the"
        };
        assertEquals(Arrays.asList(wordsNotFoundExpected2), wordsNotFound2);
    }

    public static String makeString(RedBlackTree<String> t) {
        class MyVisitor implements RedBlackTree.Visitor<String> {
            String result = "";

            public void visit(RedBlackTree.Node<String> n) {
                result = result + n.key;
            }
        }
        MyVisitor v = new MyVisitor();
        t.preOrderVisit(v);
        return v.result;
    }

    public static String makeStringDetails(RedBlackTree<String> t) {

        class MyVisitor implements RedBlackTree.Visitor<String> {
            String result = "";

            public void visit(RedBlackTree.Node<String> n) {
                if (!(n.key).equals("")) {
                    String parentKey = "";
                    if (n.parent != null) {
                        parentKey = n.parent.key;
                    }

                    result = result + "Color: " + n.color + ", Key:" + n.key + " Parent: " + parentKey + "\n";
                }
            }
        }

        MyVisitor v = new MyVisitor();
        t.preOrderVisit(v);
        return v.result;

    }
    // add this in your class
    //  public static interface Visitor
    //  {
    //  	/**
    //     This method is called at each node.
    //     @param n the visited node
    //  	 */
    //  	void visit(Node n);
    //  }


    // public void preOrderVisit(Visitor v)
    //  {
    //  	preOrderVisit(root, v);
    //  }


    // private static void preOrderVisit(Node n, Visitor v)
    //  {
    //  	if (n == null) return;
    //  	v.visit(n);
    //  	preOrderVisit(n.left, v);
    //  	preOrderVisit(n.right, v);
    //  }


}
  
