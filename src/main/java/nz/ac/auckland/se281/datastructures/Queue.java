package nz.ac.auckland.se281.datastructures;

public class Queue<T> {
  Node<T> head;
  Node<T> tail;

  public Queue() {
    this.head = null;
    this.tail = null;
  }

  public void enqueue(T data) {
    Node<T> newNode = new Node<>(data);

    if (isEmpty()) {
      head = newNode;
      tail = newNode;
    } else {
      tail.next = newNode;
      tail = newNode;
    }
  }

  public T dequeue() {
    if (isEmpty()) {
      System.out.println("Queue is empty");
      return null;
    }

    Node<T> nodeToRemove = head;
    head = head.next;

    if (head == null) {
      tail = null;
    }

    return nodeToRemove.data;
  }

  public boolean isEmpty() {
    return head == null;
  }
}
