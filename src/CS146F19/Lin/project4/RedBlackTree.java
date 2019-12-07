package CS146F19.Lin.project4;

/**
 * A generic Red Black Tree that does not use {@link java.util.Collection, Collections}
 *
 * @param <Key> generic data type for this node class which must implement {@literal Comparable<Key>}
 */
public class RedBlackTree<Key extends Comparable<Key>> implements Visitor<Key> {
    private Node<Key> root;

    /**
     * Checks if node is a leaf
     *
     * @param n node to check
     * @return true if it is a leaf, otherwise false
     */
    public boolean isLeaf(Node<Key> n) {
        if (n.equals(root) && n.getLeftChild() == null && n.getRightChild() == null) return true;
        if (n.equals(root)) return false;
        return n.getLeftChild() == null && n.getRightChild() == null;
    }

    /**
     * Prints out node's data upon visit
     *
     * @param n the visited node
     */
    public void visit(Node<Key> n) {
        System.out.println(n.getKey());
    }

    /**
     * Prints the Red Black Tree in preorder visit mode
     */
    public void printTree() {  //preorder: visit, go left, go right
        Node<Key> currentNode = root;
        printTree(currentNode);
    }

    /**
     * Helper function for {@link #printTree()}
     *
     * @param node node to visit
     */
    public void printTree(Node<Key> node) {
        if (isEmpty(node)) {
            return;
        }
        visit(node);
        if (isLeaf(node)) {
            return;
        }
        printTree(node.getLeftChild());
        printTree(node.getRightChild());
    }

    /**
     * Adds new node to Red Black Tree
     *
     * @param data data for this new node to have when added to Red Black Tree
     */
    // place a new node in the RB tree with data the parameter and color it red.
    public void addNode(Key data) {    //this < that  <0.  this > that  >0
        Node<Key> newNode = new Node<>(data);
        newNode.setColor("red");
        Node<Key> currentNode = root;
        if (isEmpty(root)) {
            root = newNode;
            fixTree(root);
        } else {
            Node<Key> currentParentNode = currentNode.getParent();
            boolean setLeftChild = true;
            while (currentNode != null) {
                if (currentNode.compareTo(newNode) > 0) {
                    currentParentNode = currentNode;
                    currentNode = currentNode.getLeftChild();
                    setLeftChild = true;
                } else {
                    currentParentNode = currentNode;
                    currentNode = currentNode.getRightChild();
                    setLeftChild = false;
                }
            }
            if (setLeftChild) {
                currentParentNode.setLeftChild(newNode);
            } else {
                currentParentNode.setRightChild(newNode);
            }
            newNode.setParent(currentParentNode);
            fixTree(newNode);
        }
    }

    /**
     * Inserts new data to Red Black Tree
     *
     * @param data data to be inserted to Red Black Tree
     */
    public void insert(Key data) {
        addNode(data);
    }

    /**
     * Looks up node with provided generic data
     *
     * @param k generic data to look up
     * @return a Node containing requested data, null if a Node containing requested data cannot be found
     */
    public Node<Key> lookup(Key k) {
        boolean found = false;
        Node<Key> foundNode = null;
        Node<Key> currentNode = root;
        while (!found) {
            // If we reached the very end of the Red Black Tree
            if (currentNode == null) {
                break;
            }
            int comparisonNumber = currentNode.getKey().compareTo(k);
            if (comparisonNumber == 0) {
                found = true;
                foundNode = currentNode;
            } else if (comparisonNumber > 0) {
                currentNode = currentNode.getLeftChild();
            } else {
                currentNode = currentNode.getRightChild();
            }
        }
        return foundNode;
    }

    /**
     * Gets the node's sibling
     *
     * @param n node to find sibling
     * @return the node's sibling
     */
    public Node<Key> getSibling(Node<Key> n) {
        if (isLeftChild(n.getParent(), n)) {
            return n.getParent().getRightChild();
        }
        return n.getParent().getLeftChild();
    }

    /**
     * Gets the node's aunt
     *
     * @param n a node to find aunt
     * @return the node's aunt
     */
    public Node<Key> getAunt(Node<Key> n) {
        return getSibling(n.getParent());
    }

    /**
     * Gets the node's grandparent
     *
     * @param n a node to find grandparent
     * @return the node's grandparent
     */
    public Node<Key> getGrandparent(Node<Key> n) {
        return n.getParent().getParent();
    }

    /**
     * Rotates part of the Red Black Tree to the left
     *
     * @param n the node to rotate
     */
    public void rotateLeft(Node<Key> n) {
        Node<Key> y = n.getRightChild();
        n.setRightChild(y.getLeftChild());
        if (y.getLeftChild() != null) {
            y.getLeftChild().setParent(n);
        }
        y.setParent(n.getParent());
        if (n.getParent() == null) {
            root = y;
        } else if (n == n.getParent().getLeftChild()) {
            n.getParent().setLeftChild(y);
        } else {
            n.getParent().setRightChild(y);
        }
        y.setLeftChild(n);
        n.setParent(y);
    }

    /**
     * Rotates part of the Red Black Tree to the right
     *
     * @param n the node to rotate
     */
    public void rotateRight(Node<Key> n) {
        Node<Key> x = n.getLeftChild();
        n.setLeftChild(x.getRightChild());
        if (x.getRightChild() != null) {
            x.getLeftChild().setParent(n);
        }
        x.setParent(n.getParent());
        if (n.getParent() == null) {
            root = x;
        } else if (n == n.getParent().getRightChild()) {
            n.getParent().setRightChild(x);
        } else {
            n.getParent().setLeftChild(x);
        }
        x.setRightChild(n);
        n.setParent(x);
    }

    /**
     * Fixes the Red Black Tree
     *
     * @param current the node to correct
     */
    public void fixTree(Node<Key> current) {
        // First case
        if (current.compareTo(root) == 0) {
            current.setColor("black");
        }
        // Second case
        else if (!current.getParent().isRed()) {
            // do nothing
        }
        // Third case
        else if (current.isRed() && current.getParent().isRed()) {
            Node<Key> grandParent = getGrandparent(current);
            Node<Key> originalParent = current.getParent();
            Node<Key> aunt = getAunt(current);
            // First major case
            if (aunt == null || !aunt.isRed()) {
                if (isLeftChild(grandParent, originalParent)) {
                    // Sub case C
                    if (isLeftChild(originalParent, current)) {
                        originalParent.setColor("black");
                        grandParent.setColor("red");
                        rotateRight(grandParent);
                    }
                    // Sub case A
                    else {
                        rotateLeft(originalParent);
                        fixTree(originalParent);
                    }
                } else {
                    // Sub case B
                    if (isLeftChild(originalParent, current)) {
                        rotateRight(originalParent);
                        fixTree(originalParent);
                    }
                    // Sub case D
                    else {
                        originalParent.setColor("black");
                        grandParent.setColor("red");
                        rotateLeft(grandParent);
                    }
                }
            } else {
                originalParent.setColor("black");
                aunt.setColor("black");
                grandParent.setColor("red");
                fixTree(grandParent);
            }
        }
    }

    /**
     * Check if node is empty
     *
     * @param n node to check
     * @return true if empty, otherwise false
     */
    public boolean isEmpty(Node<Key> n) {
        return n == null;
    }

    /**
     * Checks if node is a left child of another node
     *
     * @param parent the parent of a child
     * @param child  the child to check
     * @return true if child is the left child of parent
     */
    public boolean isLeftChild(Node<Key> parent, Node<Key> child) {
        //child is less than parent
        return child.compareTo(parent) < 0;
    }

    /**
     * Visits the Red Black Tree in preorder mode
     *
     * @param v the Visitor interface to use
     */
    public void preOrderVisit(Visitor<Key> v) {
        preOrderVisit(root, v);
    }

    /**
     * Visits each node in preorder mode
     *
     * @param n node to visit
     * @param v the Visitor interface to use
     */
    private void preOrderVisit(Node<Key> n, Visitor<Key> v) {
        if (n == null) {
            return;
        }
        v.visit(n);
        preOrderVisit(n.getLeftChild(), v);
        preOrderVisit(n.getRightChild(), v);
    }
}

