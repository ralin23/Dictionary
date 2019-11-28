package CS146F19.Lin.project4;

public class RedBlackTree<Key extends Comparable<Key>> {
    private static RedBlackTree.Node<String> root;

    public static class Node<Key extends Comparable<Key>> { //changed to static

        Key key;
        Node<String> parent;
        Node<String> leftChild;
        Node<String> rightChild;
        boolean isRed;
        int color;

        public Node(Key data) {
            this.key = data;
            leftChild = null;
            rightChild = null;
        }

        public int compareTo(Node<Key> n) {    //this < that  <0
            return key.compareTo(n.key);    //this > that  >0
        }

        public boolean isLeaf() {
            if (this.equals(root) && this.leftChild == null && this.rightChild == null) return true;
            if (this.equals(root)) return false;
            if (this.leftChild == null && this.rightChild == null) {
                return true;
            }
            return false;
        }
    }

    public boolean isLeaf(RedBlackTree.Node<String> n) {
        if (n.equals(root) && n.leftChild == null && n.rightChild == null) return true;
        if (n.equals(root)) return false;
        if (n.leftChild == null && n.rightChild == null) {
            return true;
        }
        return false;
    }

    public interface Visitor<Key extends Comparable<Key>> {
        /**
         * This method is called at each node.
         *
         * @param n the visited node
         */
        void visit(Node<Key> n);
    }

    public void visit(Node<Key> n) {
        System.out.println(n.key);
    }

    public void printTree() {  //preorder: visit, go left, go right
        RedBlackTree.Node<String> currentNode = root;
        printTree(currentNode);
    }

    public void printTree(RedBlackTree.Node<String> node) {
        System.out.print(node.key);
        if (node.isLeaf()) {
            return;
        }
        printTree(node.leftChild);
        printTree(node.rightChild);
    }

    // place a new node in the RB tree with data the parameter and color it red.
    public void addNode(String data) {    //this < that  <0.  this > that  >0
        RedBlackTree.Node<String> newNode = new RedBlackTree.Node<>(data);
        setNodeColor(newNode, "red");
        RedBlackTree.Node<String> currentNode = root;
        if (root == null) {
            root = newNode;
            fixTree(root);
        } else {
            RedBlackTree.Node<String> currentParentNode = currentNode.parent;
            boolean setLeftChild = true;
            while (currentNode != null) {
                if (currentNode.compareTo(newNode) > 0) {
                    currentParentNode = currentNode;
                    currentNode = currentNode.leftChild;
                    setLeftChild = true;
                } else {
                    currentParentNode = currentNode;
                    currentNode = currentNode.rightChild;
                    setLeftChild = false;
                }
            }
            if (setLeftChild) {
                currentParentNode.leftChild = newNode;
                newNode.parent = currentParentNode;
            } else {
                currentParentNode.rightChild = newNode;
                newNode.parent = currentParentNode;
            }
            fixTree(newNode);
        }
    }

    public void insert(String data) {
        addNode(data);
    }

    public RedBlackTree.Node<String> lookup(String k) {
        boolean found = false;
        RedBlackTree.Node<String> foundNode = null;
        RedBlackTree.Node<String> currentNode = root;
        while (!found) {
            // If we reached the very end of the Red Black Tree
            if (currentNode == null) {
                break;
            }
            int comparisonNumber = currentNode.key.compareTo(k);
            if (comparisonNumber == 0) {
                found = true;
                foundNode = currentNode;
            } else if (comparisonNumber > 0) {
                currentNode = currentNode.leftChild;
            } else {
                currentNode = currentNode.rightChild;
            }
        }
        return foundNode;
    }


    public RedBlackTree.Node<String> getSibling(RedBlackTree.Node<String> n) {
        if (isLeftChild(n.parent, n)) {
            return n.parent.rightChild;
        }
        return n.parent.leftChild;
    }


    public RedBlackTree.Node<String> getAunt(RedBlackTree.Node<String> n) {
        return getSibling(n.parent);
    }

    public RedBlackTree.Node<String> getGrandparent(RedBlackTree.Node<String> n) {
        return n.parent.parent;
    }

    public void rotateLeft(RedBlackTree.Node<String> n) {
        RedBlackTree.Node<String> y = n.rightChild;
        n.rightChild = y.leftChild;
        if (y.leftChild != null) {
            y.leftChild.parent = n;
        }
        y.parent = n.parent;
        if (n.parent == null) {
            root = y;
        } else if (n == n.parent.leftChild) {
            n.parent.leftChild = y;
        } else {
            n.parent.rightChild = y;
        }
        y.leftChild = n;
        n.parent = y;
    }

    public void rotateRight(RedBlackTree.Node<String> n) {
        RedBlackTree.Node<String> x = n.leftChild;
        n.leftChild = x.rightChild;
        if (x.rightChild != null) {
            x.rightChild.parent = n;
        }
        x.parent = n.parent;
        if (n.parent == null) {
            root = x;
        } else if (n == n.parent.rightChild) {
            n.parent.rightChild = x;
        } else {
            n.parent.leftChild = x;
        }
        x.rightChild = n;
        n.parent = x;
    }

    public void fixTree(RedBlackTree.Node<String> current) {
        // First case
        if (current.compareTo(root) == 0) {
            setNodeColor(current, "black");
        }
        // Second case
        else if (!current.parent.isRed) {
            // do nothing
        }
        // Third case
        else if (current.isRed && current.parent.isRed) {
            RedBlackTree.Node<String> grandParent = getGrandparent(current);
            RedBlackTree.Node<String> originalParent = current.parent;
            RedBlackTree.Node<String> aunt = getAunt(current);
            // First major case
            if (aunt == null || !aunt.isRed) {
                if (isLeftChild(grandParent, originalParent)) {
                    // Sub case C
                    if (isLeftChild(originalParent, current)) {
                        setNodeColor(originalParent, "black");
                        setNodeColor(grandParent, "red");
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
                        setNodeColor(originalParent, "black");
                        setNodeColor(grandParent, "red");
                        rotateLeft(grandParent);
                    }
                }
            } else {
                setNodeColor(originalParent, "black");
                setNodeColor(aunt, "black");
                setNodeColor(grandParent, "red");
                fixTree(grandParent);
            }
        }
    }

    public void setNodeColor(RedBlackTree.Node<String> n, String color) {
        color = color.toLowerCase();
        if (color.compareTo("red") == 0) {
            n.isRed = true;
            n.color = 0;
        } else {
            n.isRed = false;
            n.color = 1;
        }
    }

    public boolean isEmpty(RedBlackTree.Node<String> n) {
        if (n.key == null) {
            return true;
        }
        return false;
    }

    public boolean isLeftChild(RedBlackTree.Node<String> parent, RedBlackTree.Node<String> child) {
        if (child.compareTo(parent) < 0) {//child is less than parent
            return true;
        }
        return false;
    }

    public void preOrderVisit(Visitor<String> v) {
        preOrderVisit(root, v);
    }


    private static void preOrderVisit(RedBlackTree.Node<String> n, Visitor<String> v) {
        if (n == null) {
            return;
        }
        v.visit(n);
        preOrderVisit(n.leftChild, v);
        preOrderVisit(n.rightChild, v);
    }
}

