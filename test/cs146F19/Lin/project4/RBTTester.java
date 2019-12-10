package cs146F19.Lin.project4;

import static org.junit.Assert.*;

import org.junit.Test;

import java.util.*;

/**
 * Tests the {@link RedBlackTree} using various tests.
 */
public class RBTTester {

    @Test
    //Test the Red Black Tree using Strings
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
    //Test Spell Check
    public void spellCheckTest() {
        FileRead fileReader = new FileRead();
        RedBlackTree<String> dictionary = fileReader.readDictionaryFile("dictionary/dictionary.txt");
        System.out.println("Start Spell Check for Small Sample (4 words): ");
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
        System.out.println("OK.");
        System.out.println("Start Spell Check for Sample Poem (sampleInputs/input2.txt): ");
        List<String> wordsInput2 = fileReader.readInput("sampleInputs/input2.txt");
        List<String> wordsNotFound2 = spellCheck.spellCheck(dictionary, wordsInput2);
        String[] wordsNotFoundExpected2 = {
        		"you", "fit", "me", "a", "an", "eye", "a", "an", "eye"
        };
        assertEquals(Arrays.asList(wordsNotFoundExpected2), wordsNotFound2);
        System.out.println("OK.");
    }

    @Test
    // Tests Red Black Tree using Number class
    public void genericTest() {
        RedBlackTree<Number> numbersRedBlackTree = new RedBlackTree<>();
        numbersRedBlackTree.addNode(new Number(9));
        numbersRedBlackTree.addNode(new Number(100));
        numbersRedBlackTree.addNode(new Number(20));
        numbersRedBlackTree.addNode(new Number(1));
        numbersRedBlackTree.addNode(new Number(50));
        numbersRedBlackTree.addNode(new Number(1000));
        numbersRedBlackTree.addNode(new Number(500));
        numbersRedBlackTree.addNode(new Number(250));
        String str = "Number: 20\n" +
                "Number: 100\n" +
                "Number: 500\n" +
                "Number: 1000\n" +
                "Number: 250\n" +
                "Number: 50\n" +
                "Number: 9\n" +
                "Number: 1\n";
        assertEquals(str, makeNumberDetails(numbersRedBlackTree));
    }

    public static String makeString(RedBlackTree<String> t) {
        class MyVisitor<Key extends Comparable<Key>> implements Visitor<Key> {
            String result = "";

            public void visit(Node<Key> n) {
                result = result + n.getKey();
            }
        }
        MyVisitor<String> v = new MyVisitor<>();
        t.preOrderVisit(v);
        return v.result;
    }

    public static String makeStringDetails(RedBlackTree<String> t) {
        class MyVisitor<Key extends Comparable<Key>> implements Visitor<Key> {
            String result = "";

            public void visit(Node<Key> n) {
                if (!(n.getKey()).equals("")) {
                    String parentKey = "";
                    if (n.getParent() != null) {
                        parentKey = n.getParent().getKey().toString();
                    }
                    result = result + "Color: " + n.getColor() + ", Key:" + n.getKey() + " Parent: " + parentKey + "\n";
                }
            }
        }
        MyVisitor<String> v = new MyVisitor<>();
        t.preOrderVisit(v);
        return v.result;
    }

    public static String makeNumberDetails(RedBlackTree<Number> t) {
        class MyVisitor<Key extends Comparable<Key>> implements Visitor<Key> {
            String result = "";

            public void visit(Node<Key> n) {
                if (!(n.getKey()).equals("")) {
                    result = result + n.getKey().toString() + "\n";
                }
            }
        }
        MyVisitor<Number> v = new MyVisitor<>();
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
  
