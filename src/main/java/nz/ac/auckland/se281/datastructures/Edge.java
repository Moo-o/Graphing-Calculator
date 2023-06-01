package nz.ac.auckland.se281.datastructures;

/**
 * An edge in a graph that connects two verticies.
 *
 * <p>You must NOT change the signature of the constructor of this class.
 *
 * @param <T> The type of each vertex.
 */
public class Edge<T> {
  /**
   * Creates a new edge between two verticies.
   *
   * @param source The source vertex.
   * @param destination The destination vertex.
   */
  protected final T source;

  protected final T destination;

  public Edge(T source, T destination) {
    this.source = source;
    this.destination = destination;
  }

  public T getSource() {
    return source;
  }

  public T getDestination() {
    return destination;
  }
}
