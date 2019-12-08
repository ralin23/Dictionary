package cs146F19.Lin.project4;

import java.awt.Color;

/**
 * This class holds generic node data for {@link RedBlackTree Red-Black Tree}.
 *
 * @param <Key> generic data type for this node class which must implement {@literal Comparable<Key>}.
 */
public class Node<Key extends Comparable<Key>> { //changed to static

    private Key key;
    private Node<Key> parent;
    private Node<Key> leftChild;
    private Node<Key> rightChild;
    private boolean isRed;
    private Color color;

    /**
     * Creates a node with generic data
     *
     * @param data data for node to hold
     */
    public Node(Key data) {
        this.key = data;
        this.leftChild = null;
        this.rightChild = null;
    }

    /**
     * Compares two nodes
     *
     * @param n the node to compare against
     * @return an integer to tell which node comes first
     */
    public int compareTo(Node<Key> n) {    //this < that  <0
        return key.compareTo(n.key);    //this > that  >0
    }

    /**
     * Gets the generic data from the node
     *
     * @return generic data from the node
     */
    public Key getKey() {
        return key;
    }

    /**
     * Gets the parent node of this node
     *
     * @return the parent node
     */
    public Node<Key> getParent() {
        return parent;
    }

    /**
     * Gets the left child node of this node
     *
     * @return the left child node
     */
    public Node<Key> getLeftChild() {
        return leftChild;
    }

    /**
     * Gets the right child node of this node
     *
     * @return the right child node
     */
    public Node<Key> getRightChild() {
        return rightChild;
    }

    /**
     * Checks if the node is red
     *
     * @return true if the node is red, false if the node is black
     */
    public boolean isRed() {
        return isRed;
    }

    /**
     * Gets the color of this node as an integer
     *
     * @return integer 0 if it is red, integer 1 if it is black
     */
    public int getColor() {
        if (color == Color.RED) {
            return 0;
        }
        return 1;
    }

    /**
     * Sets specified node as this node's parent
     *
     * @param parent the parent node for this node
     */
    public void setParent(Node<Key> parent) {
        this.parent = parent;
    }

    /**
     * Sets specified node as this node's left child
     *
     * @param leftChild the left child node for this node
     */
    public void setLeftChild(Node<Key> leftChild) {
        this.leftChild = leftChild;
    }

    /**
     * Sets specified node as this node's right child
     *
     * @param rightChild the right child node for this node
     */
    public void setRightChild(Node<Key> rightChild) {
        this.rightChild = rightChild;
    }

    /**
     * Sets the color of the node to either Red or Black
     *
     * @param color a String with the color name "red" or "black"
     */
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