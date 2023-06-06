package nz.ac.auckland.se281.datastructures;

public class Queue<T> {
  Node<T> head;
  Node<T> tail;

  public Queue() {
    this.head = null;
    this.tail = null;
  }

  public void enqueue(T data) {
    // create a new node
    Node<T> newNode = new Node<>(data);

    // if the queue is empty, set the head and tail to the new node
    if (isEmpty()) {
      head = newNode;
      tail = newNode;
    } else {
      tail.next = newNode;
      tail = newNode;
    }
  }

  public T dequeue() {
    // if the queue is empty, return null
    if (isEmpty()) {
      System.out.println("Queue is empty");
      return null;
    }

    // if the queue is not empty, remove the head node
    Node<T> nodeToRemove = head;
    head = head.next;

    // if the queue is now empty, set the tail to null
    if (head == null) {
      tail = null;
    }

    // return the data of the removed node
    return nodeToRemove.data;
  }

  public boolean isEmpty() {
    return head == null;
  }
}
