package CS146F19.Lin.project4;

import static org.junit.Assert.*;

import org.junit.Test;

import java.util.*;


public class RBTTester {

    @Test
    //Test the Red Black Tree
    public void test() {
        RedBlackTree rbt = new RedBlackTree();
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
        RedBlackTree dictionary = fileReader.readDictionaryFile("dictonary/dictionary.txt");
        List<String> wordsInput = new ArrayList<>();
        wordsInput.add("hello");
        wordsInput.add("hellishly");
        wordsInput.add("zzzzzzzzzzz");
        wordsInput.add("zywiel");
        List<String> wordsNotFound = new ArrayList<>();
        for (String x : wordsInput) {
            RedBlackTree.Node node = dictionary.lookup(x);
            if (node == null) {
                wordsNotFound.add(x);
            }
        }
        List<String> wordsNotFoundExpected = new ArrayList<>();
        wordsNotFoundExpected.add("zzzzzzzzzzz");
        assertEquals(wordsNotFoundExpected, wordsNotFound);
        /*List<String> wordsInput = fileReader.readInput("sampleInputs/poem1.txt");
        List<String> wordsNotFound = new ArrayList<>();
        for(String x: wordsInput) {
            RedBlackTree.Node node = dictionary.lookup(x);
            if(node == null) {
                wordsNotFound.add(x);
            }
        }*/

    }

    public static String makeString(RedBlackTree t) {
        class MyVisitor implements RedBlackTree.Visitor {
            String result = "";

            public void visit(RedBlackTree.Node n) {
                result = result + n.key;
            }
        }
        ;
        MyVisitor v = new MyVisitor();
        t.preOrderVisit(v);
        return v.result;
    }

    public static String makeStringDetails(RedBlackTree t) {

        class MyVisitor implements RedBlackTree.Visitor {
            String result = "";

            public void visit(RedBlackTree.Node n) {
                if (!(n.key).equals("")) {
                    String parentKey = "";
                    if (n.parent != null) {
                        parentKey = n.parent.key.toString();
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
  
