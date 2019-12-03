package CS146F19.Lin.project4;

public class Generic {
    public static void main(String[] args) {
        RedBlackTree<Numbers> numbersRedBlackTree = new RedBlackTree<>();
        numbersRedBlackTree.addNode(new Numbers(9));
        numbersRedBlackTree.addNode(new Numbers(100));
        numbersRedBlackTree.addNode(new Numbers(20));
        numbersRedBlackTree.addNode(new Numbers(1));
        numbersRedBlackTree.addNode(new Numbers(50));
        numbersRedBlackTree.addNode(new Numbers(1000));
        numbersRedBlackTree.addNode(new Numbers(500));
        numbersRedBlackTree.addNode(new Numbers(250));
        numbersRedBlackTree.printTree();
    }
}
