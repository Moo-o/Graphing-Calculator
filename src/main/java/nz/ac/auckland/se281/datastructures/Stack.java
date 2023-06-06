package nz.ac.auckland.se281.datastructures;

/**
 * A stack data structure implementation.
 *
 * @param <T> the type of elements in the stack
 */
public class Stack<T> {
  private Node<T> top;

  /**
   * Pushes an item onto the top of the stack.
   *
   * @param item the item to be pushed onto the stack
   */
  public void push(T item) {
    Node<T> newNode = new Node<>(item);
    newNode.next = top;
    top = newNode;
  }

  /**
   * Removes and returns the item at the top of the stack.
   *
   * @return the item at the top of the stack, or {@code null} if the stack is empty
   */
  public T pop() {
    if (isEmpty()) {
      System.out.println("Stack is empty");
      return null;
    }
    T data = top.data;
    top = top.next;
    return data;
  }

  /**
   * Returns the item at the top of the stack without removing it.
   *
   * @return the item at the top of the stack, or {@code null} if the stack is empty
   */
  public T peek() {
    if (isEmpty()) {
      System.out.println("Stack is empty");
      return null;
    }
    return top.data;
  }

  /**
   * Checks if the stack is empty.
   *
   * @return {@code true} if the stack is empty, {@code false} otherwise
   */
  public boolean isEmpty() {
    return top == null;
  }
}
