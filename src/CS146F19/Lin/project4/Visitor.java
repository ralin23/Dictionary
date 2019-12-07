package CS146F19.Lin.project4;

/**
 * Any graph that implements this interface needs to have visitable nodes.
 *
 * @param <Key> generic data type for this node class which must implement {@literal Comparable<Key>}
 */
public interface Visitor<Key extends Comparable<Key>> {
    /**
     * This method is called at each node.
     *
     * @param n the visited node
     */
    void visit(Node<Key> n);
}