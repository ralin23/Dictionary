package CS146F19.Lin.project4;

import java.awt.Color;

public class Node<Key extends Comparable<Key>> { //changed to static

    private Key key;
    private Node<Key> parent;
    private Node<Key> leftChild;
    private Node<Key> rightChild;
    private boolean isRed;
    private Color color;

    public Node(Key data) {
        this.key = data;
        this.leftChild = null;
        this.rightChild = null;
    }

    public int compareTo(Node<Key> n) {    //this < that  <0
        return key.compareTo(n.key);    //this > that  >0
    }

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
        if (color == Color.RED) {
            return 0;
        }
        return 1;
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

    public void setColor(String color) {
        if (color.toLowerCase().compareTo("red") == 0) {
            this.isRed = true;
            this.color = Color.RED;
        } else {
            this.isRed = false;
            this.color = Color.BLACK;
        }
    }

}