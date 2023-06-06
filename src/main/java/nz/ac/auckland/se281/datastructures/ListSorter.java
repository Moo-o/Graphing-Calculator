package nz.ac.auckland.se281.datastructures;

import java.util.List;

/**
 * A utility class for sorting a list of comparable elements using the bubble sort algorithm.
 *
 * @param <T> the type of elements in the list, which must implement the Comparable interface
 */
public class ListSorter<T extends Comparable<T>> {

  /**
   * Sorts the specified list in ascending order using the bubble sort algorithm.
   *
   * @param list the list to be sorted
   */
  public void bubbleSort(List<T> list) {
    int n = list.size();
    boolean swapped;

    for (int i = 0; i < n - 1; i++) {
      swapped = false;

      for (int j = 0; j < n - i - 1; j++) {
        if (list.get(j).compareTo(list.get(j + 1)) > 0) {
          // Swap list[j] and list[j+1]
          T temp = list.get(j);
          list.set(j, list.get(j + 1));
          list.set(j + 1, temp);
          swapped = true;
        }
      }

      // If no two elements were swapped in the inner loop, the list is already sorted
      if (!swapped) {
        break;
      }
    }
  }
}
