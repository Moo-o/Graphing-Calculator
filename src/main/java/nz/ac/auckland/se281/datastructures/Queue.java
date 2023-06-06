package nz.ac.auckland.se281.datastructures;

/**
 * A generic queue data structure.
 *
 * @param <T> the type of elements held in the queue
 */
public class Queue<T> {
  protected Node<T> head;
  protected Node<T> tail;

  /** Constructs an empty queue. */
  public Queue() {
    this.head = null;
    this.tail = null;
  }

  /**
   * Adds the specified data to the end of the queue.
   *
   * @param data the data to be added to the queue
   */
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

  /**
   * Removes and returns the element at the front of the queue.
   *
   * @return the element at the front of the queue, or {@code null} if the queue is empty
   */
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

  /**
   * Checks if the queue is empty.
   *
   * @return {@code true} if the queue is empty, {@code false} otherwise
   */
  public boolean isEmpty() {
    return head == null;
  }
}
