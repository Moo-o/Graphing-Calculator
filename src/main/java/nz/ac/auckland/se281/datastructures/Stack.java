package nz.ac.auckland.se281.datastructures;

public class Stack<T> {
  private Node<T> top;

  public void push(T item) {
    Node<T> newNode = new Node<>(item);
    newNode.next = top;
    top = newNode;
  }

  public T pop() {
    if (isEmpty()) {
      System.out.println("Stack is empty");
      return null;
    }
    T data = top.data;
    top = top.next;
    return data;
  }

  public T peek() {
    if (isEmpty()) {
      System.out.println("Stack is empty");
      return null;
    }
    return top.data;
  }

  public boolean isEmpty() {
    return top == null;
  }
}
