package nz.ac.auckland.se281.datastructures;

/**
 * Represents a node in a linked list.
 *
 * @param <T> the type of data stored in the node
 */
public class Node<T> {
  protected T data;
  protected Node<T> next;

  /**
   * Constructs a new node with the specified data.
   *
   * @param data the data to be stored in the node
   */
  public Node(T data) {
    this.data = data;
    this.next = null;
  }
}
