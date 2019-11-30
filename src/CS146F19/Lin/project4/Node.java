package CS146F19.Lin.project4;

public class Node<Key extends Comparable<Key>> { //changed to static

    private Key key;
    private Node<Key> parent;
    private Node<Key> leftChild;
    private Node<Key> rightChild;
    private boolean isRed;
    private int color;

    public Node(Key data) {
        this.key = data;
        leftChild = null;
        rightChild = null;
    }

    public int compareTo(Node<Key> n) {    //this < that  <0
        return key.compareTo(n.key);    //this > that  >0
    }

    /*public boolean isLeaf() {
        if (this.equals(root) && this.leftChild == null && this.rightChild == null) return true;
        if (this.equals(root)) return false;
        if (this.leftChild == null && this.rightChild == null) {
            return true;
        }
        return false;
    }*/

    public Key getKey() {
        return key;
    }

    public Node<Key> getParent() {
        return parent;
    }

    public Node<Key> getLeftChild() {
        return leftChild;
    }

    public Node<Key> getRightChild() {
        return rightChild;
    }

    public boolean isRed() {
        return isRed;
    }

    public int getColor() {
        return color;
    }

    public void setParent(Node<Key> parent) {
        this.parent = parent;
    }

    public void setLeftChild(Node<Key> leftChild) {
        this.leftChild = leftChild;
    }

    public void setRightChild(Node<Key> rightChild) {
        this.rightChild = rightChild;
    }

    public void setColor(boolean red, int color) {
        this.isRed = red;
        this.color = color;
    }

}