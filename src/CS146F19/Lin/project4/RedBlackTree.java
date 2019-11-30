package CS146F19.Lin.project4;

public class RedBlackTree<Key extends Comparable<Key>> implements Visitor<Key> {
    private Node<Key> root;

    public boolean isLeaf(Node<Key> n) {
        if (n.equals(root) && n.getLeftChild() == null && n.getRightChild() == null) return true;
        if (n.equals(root)) return false;
        if (n.getLeftChild() == null && n.getRightChild() == null) {
            return true;
        }
        return false;
    }

    public void visit(Node<Key> n) {
        System.out.println(n.getKey());
    }

    public void printTree() {  //preorder: visit, go left, go right
        Node<Key> currentNode = root;
        printTree(currentNode);
    }

    public void printTree(Node<Key> node) {
        System.out.print(node.getKey());
        if (isLeaf(node)) {
            return;
        }
        printTree(node.getLeftChild());
        printTree(node.getRightChild());
    }

    // place a new node in the RB tree with data the parameter and color it red.
    public void addNode(Key data) {    //this < that  <0.  this > that  >0
        //	fill

    }

    public void insert(Key data) {
        addNode(data);
    }

    public Node<Key> lookup(Key k) {
        //fill
    }


    public Node<Key> getSibling(Node<Key> n) {
        //
    }


    public Node<Key> getAunt(Node<Key> n) {
        //
    }

    public Node<Key> getGrandparent(Node<Key> n) {
        return n.getParent().getParent();
    }

    public void rotateLeft(Node<Key> n) {
        //
    }

    public void rotateRight(Node<Key> n) {
        //
    }

    public void fixTree(Node<Key> current) {
        //
    }

    public boolean isEmpty(Node<Key> n) {
        if (n.getKey() == null) {
            return true;
        }
        return false;
    }

    public boolean isLeftChild(Node<Key> parent, Node<Key> child) {
        if (child.compareTo(parent) < 0) {//child is less than parent
            return true;
        }
        return false;
    }

    public void preOrderVisit(Visitor<String> v) {
        preOrderVisit(root, v);
    }


    private void preOrderVisit(Node<Key> n, Visitor<Key> v) {
        if (n == null) {
            return;
        }
        v.visit(n);
        preOrderVisit(n.getLeftChild(), v);
        preOrderVisit(n.getRightChild(), v);
    }
}

