package nz.ac.auckland.se281.datastructures;

public class Node<T> {
  protected T data;
  protected Node<T> next;

  public Node(T data) {
    this.data = data;
    this.next = null;
  }
}
