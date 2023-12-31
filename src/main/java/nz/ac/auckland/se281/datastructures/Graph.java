package nz.ac.auckland.se281.datastructures;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * A graph that is composed of a set of verticies and edges.
 *
 * <p>You must NOT change the signature of the existing methods or constructor of this class.
 *
 * @param <T> The type of each vertex, that have a total ordering.
 */
public class Graph<T extends Comparable<T>> {
  protected final Set<T> verticies;
  protected final Set<Edge<T>> edges;

  public Graph(Set<T> verticies, Set<Edge<T>> edges) {
    this.verticies = verticies;
    this.edges = edges;
  }

  /**
   * finds the roots of a graph.
   *
   * @return returns the set of verticies that are roots
   */
  public Set<T> getRoots() {
    Set<T> lowestVertices = new HashSet<>();

    if (isEquivalence()) {
      for (T vertex : verticies) {
        Set<T> equivalenceClass = getEquivalenceClass(vertex);
        T lowestVertex = findLowestVertex(equivalenceClass);
        lowestVertices.add(lowestVertex);
      }
    } else {
      // find the verticies with no incoming edges and at least one outgoing edge using getSource
      // and getDestination
      for (T vertex : verticies) {
        boolean hasIncomingEdge = false;
        boolean hasOutgoingEdge = false;
        for (Edge<T> edge : edges) {
          if (edge.getDestination().equals(vertex)) {
            hasIncomingEdge = true;
          }
          if (edge.getSource().equals(vertex)) {
            hasOutgoingEdge = true;
          }
        }
        if (!hasIncomingEdge && hasOutgoingEdge) {
          lowestVertices.add(vertex);
        }
      }
    }

    return lowestVertices;
  }

  private T findLowestVertex(Set<T> vertices) {
    T lowestVertex = null;
    for (T vertex : vertices) {
      if (lowestVertex == null || vertex.compareTo(lowestVertex) < 0) {
        lowestVertex = vertex;
      }
    }
    return lowestVertex;
  }

  /**
   * Checks if the graph represented by the vertices and edges is reflexive.
   *
   * @return {@code true} if the graph is reflexive, {@code false} otherwise
   */
  public boolean isReflexive() {
    // checks if the graph is reflexive
    int reflexiveCount = 0;
    for (T vertex : verticies) {
      for (Edge<T> edge : edges) {
        if (edge.getSource().equals(vertex) && edge.getDestination().equals(vertex)) {
          reflexiveCount++;
        }
      }
    }
    if (reflexiveCount == verticies.size()) {
      return true;
    } else {
      return false;
    }
  }

  /**
   * Checks if the graph represented by the edges is symmetric.
   *
   * @return {@code true} if the graph is symmetric, {@code false} otherwise
   */
  public boolean isSymmetric() {
    int symmetricCount = 0;

    // checks if the graph is symmetric
    for (Edge<T> edge : edges) {
      for (Edge<T> edge2 : edges) {
        if (edge.getSource().equals(edge2.getDestination())
            && edge.getDestination().equals(edge2.getSource())) {
          symmetricCount++;
        }
      }
    }

    if (symmetricCount == edges.size()) {
      return true;
    } else {
      return false;
    }
  }

  /**
   * Checks if the graph represented by the edges has a transitive relationship.
   *
   * @return {@code true} if the transitive relationship exists for all pairs of edges, {@code
   *     false} otherwise
   */
  public boolean isTransitive() {
    for (Edge<T> edge1 : edges) {
      for (Edge<T> edge2 : edges) {
        if (edge1.getDestination().equals(edge2.getSource())) {
          boolean transitiveExists = false;
          for (Edge<T> edge3 : edges) {
            if (edge1.getSource().equals(edge3.getSource())
                && edge2.getDestination().equals(edge3.getDestination())) {
              transitiveExists = true;
              break;
            }
          }
          if (!transitiveExists) {
            return false; // Transitive relationship does not exist
          }
        }
      }
    }

    return true; // All pairs satisfy the transitive relationship
  }

  /**
   * Checks if the relation represented by the graph is anti-symmetric.
   *
   * @return {@code true} if the relation is anti-symmetric, {@code false} otherwise
   */
  public boolean isAntiSymmetric() {
    for (Edge<T> edge1 : edges) {
      for (Edge<T> edge2 : edges) {
        if (edge1.getSource().equals(edge2.getDestination())
            && edge1.getDestination().equals(edge2.getSource())) {
          if (!edge1.getSource().equals(edge1.getDestination())) {
            return false; // Anti-symmetric relationship does not exist
          }
        }
      }
    }
    return true; // All pairs satisfy the anti-symmetric relationship
  }

  /**
   * Checks if the relation represented by the graph is an equivalence relation.
   *
   * @return {@code true} if the relation is an equivalence relation, {@code false} otherwise
   */
  public boolean isEquivalence() {
    // check for equivalence
    if (isReflexive() && isSymmetric() && isTransitive()) {
      return true;
    } else {
      return false;
    }
  }

  /**
   * Retrieves the equivalence class for the specified vertex.
   *
   * @param vertex the vertex for which to retrieve the equivalence class
   * @return a set containing the vertices that belong to the equivalence class of the specified
   *     vertex, or an empty set if the graph is not an equivalence relation
   */
  public Set<T> getEquivalenceClass(T vertex) {
    // get equivalence class
    Set<T> equivalenceClass = new HashSet<T>();
    if (isEquivalence()) {
      for (Edge<T> edge : edges) {
        if (edge.getSource().equals(vertex)) {
          equivalenceClass.add(edge.getDestination());
        }
      }
      return equivalenceClass;
    } else {
      return equivalenceClass;
    }
  }

  /**
   * Performs an iterative breadth-first search on the graph and returns a list of vertices visited
   * in the search order.
   *
   * @return a list of vertices visited during the iterative breadth-first search
   */
  public List<T> iterativeBreadthFirstSearch() {
    List<T> result = new ArrayList<>();
    Set<T> visited = new HashSet<>();
    Queue<T> queue = new Queue<>();

    // Choose a starting vertex
    // Assuming you have a method to get the starting vertex, let's call it getStartingVertex()
    Set<T> roots = getRoots();
    for (T startingVertex : roots) {

      // Enqueue the starting vertex and mark it as visited
      queue.enqueue(startingVertex);
      visited.add(startingVertex);

      while (!queue.isEmpty()) {
        // Dequeue a vertex from the queue
        T vertex = queue.dequeue();

        // Process the vertex (e.g., add it to the result list)
        result.add(vertex);

        // Get the adjacent vertices of the current vertex
        List<T> adjacentVertices = getAdjacentVertices(vertex);
        ListSorter<T> sorter = new ListSorter<>();
        sorter.bubbleSort(adjacentVertices);

        for (T adjacentVertex : adjacentVertices) {
          if (!visited.contains(adjacentVertex)) {
            // Enqueue the adjacent vertex if it hasn't been visited
            queue.enqueue(adjacentVertex);
            visited.add(adjacentVertex);
          }
        }
      }
    }

    return result;
  }

  private List<T> getAdjacentVertices(T vertex) {
    // return list of adjacent vertices
    List<T> adjacentVertices = new ArrayList<T>();
    for (Edge<T> edge : edges) {
      if (edge.getSource().equals(vertex)) {
        adjacentVertices.add(edge.getDestination());
      }
    }
    return adjacentVertices;
  }

  /**
   * Performs an iterative depth-first search on the graph and returns a list of vertices visited in
   * the search order.
   *
   * @return a list of vertices visited during the iterative depth-first search
   */
  public List<T> iterativeDepthFirstSearch() {
    List<T> result = new ArrayList<>();
    Set<T> visited = new HashSet<>();
    Stack<T> stack = new Stack<>();

    // Choose a starting vertex
    // Assuming you have a method to get the starting vertex, let's call it getStartingVertex()
    Set<T> roots = getRoots();
    for (T startingVertex : roots) {

      // Push the starting vertex onto the stack and mark it as visited
      stack.push(startingVertex);
      visited.add(startingVertex);

      while (!stack.isEmpty()) {
        // Pop a vertex from the stack
        T vertex = stack.pop();

        // Process the vertex (e.g., add it to the result list)
        result.add(vertex);

        // Get the adjacent vertices of the current vertex
        List<T> adjacentVertices = getAdjacentVertices(vertex);
        ListDescender<T> sorter = new ListDescender<>();
        sorter.bubbleSort(adjacentVertices);

        for (T adjacentVertex : adjacentVertices) {
          if (!visited.contains(adjacentVertex)) {
            // Push the adjacent vertex onto the stack if it hasn't been visited
            stack.push(adjacentVertex);
            visited.add(adjacentVertex);
          }
        }
      }
    }

    return result;
  }

  /**
   * Performs a recursive breadth-first search on the graph and returns a list of vertices visited
   * in the search order.
   *
   * @return a list of vertices visited during the recursive breadth-first search
   */
  public List<T> recursiveBreadthFirstSearch() {
    List<T> result = new ArrayList<>();
    Set<T> visited = new HashSet<>();
    Queue<T> queue = new Queue<>();

    // Choose a starting vertex
    // Assuming you have a method to get the starting vertex, let's call it getStartingVertex()
    Set<T> roots = getRoots();
    for (T startingVertex : roots) {
      recursiveSearchBreadth(startingVertex, visited, queue, result);
    }

    return result;
  }

  private void recursiveSearchBreadth(T vertex, Set<T> visited, Queue<T> queue, List<T> result) {
    visited.add(vertex);
    result.add(vertex);

    // Get the adjacent vertices of the current vertex
    List<T> adjacentVertices = getAdjacentVertices(vertex);
    ListSorter<T> sorter = new ListSorter<>();
    sorter.bubbleSort(adjacentVertices);

    for (T adjacentVertex : adjacentVertices) {
      if (!visited.contains(adjacentVertex)) {
        visited.add(adjacentVertex);
        queue.enqueue(adjacentVertex);
      }
    }

    // Process the next vertex in the queue recursively
    if (!queue.isEmpty()) {
      T nextVertex = queue.dequeue();
      recursiveSearchBreadth(nextVertex, visited, queue, result);
    }
  }

  /**
   * Performs a recursive depth-first search on the graph and returns a list of vertices visited in
   * the search order.
   *
   * @return a list of vertices visited during the recursive depth-first search
   */
  public List<T> recursiveDepthFirstSearch() {
    List<T> result = new ArrayList<>();
    Set<T> visited = new HashSet<>();

    // Choose a starting vertex
    // Assuming you have a method to get the starting vertex, let's call it getStartingVertex()
    Set<T> roots = getRoots();
    for (T startingVertex : roots) {
      recursiveSearchDepth(startingVertex, visited, result);
    }

    return result;
  }

  private void recursiveSearchDepth(T vertex, Set<T> visited, List<T> result) {
    visited.add(vertex);
    result.add(vertex);

    // Get the adjacent vertices of the current vertex
    List<T> adjacentVertices = getAdjacentVertices(vertex);
    ListSorter<T> sorter = new ListSorter<>();
    sorter.bubbleSort(adjacentVertices);

    for (T adjacentVertex : adjacentVertices) {
      if (!visited.contains(adjacentVertex)) {
        recursiveSearchDepth(adjacentVertex, visited, result);
      }
    }
  }
}
