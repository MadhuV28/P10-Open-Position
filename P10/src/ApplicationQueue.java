/////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: ApplicationQueue
// Course: CS 300 Spring 2022
//
// Author: Madhu Vuyyuru
// Email: mvuyyuru@wisc.edu
// Lecturer: Hobbes LeGault
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// pair programming was not used for this assignment
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// no outside source code was used
//
//
///////////////////////////////////////////////////////////////////////////////
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Array-based heap implementation of a priority queue containing Applications. Guarantees the
 * min-heap invariant, so that the Application at the root should have the lowest score, and
 * children always have a higher or equal score as their parent. The root of a non-empty queue is
 * always at index 0 of this array-heap.
 * 
 * @author Madhu Vuyyuru
 */
public class ApplicationQueue implements PriorityQueueADT<Application>, Iterable<Application> {
  private Application[] queue; // array min-heap of applications representing this priority queue
  private int size; // size of this priority queue

  /**
   * Creates a new empty ApplicationQueue with the given capacity
   * 
   * @param capacity Capacity of this ApplicationQueue
   * @throws IllegalArgumentException with a descriptive error message if the capacity is not a
   *                                  positive integer
   */
  public ApplicationQueue(int capacity) {
    // TODO verify the capacity
    if (capacity <= 0) {
      throw new IllegalArgumentException("The capacity is not a positive integer");
    }
    queue = new Application[capacity];
    this.size = 0;
    // TODO initialize fields appropriately

  }

  /**
   * Checks whether this ApplicationQueue is empty
   * 
   * @return {@code true} if this ApplicationQueue is empty
   */
  @Override
  public boolean isEmpty() {
    if (size() == 0) {
      return true;
    }
    return false; // TODO fix
  }

  /**
   * Returns the size of this ApplicationQueue
   * 
   * @return the size of this ApplicationQueue
   */
  @Override
  public int size() {
    return size; // TODO fix
  }

  /**
   * Adds the given Application to this ApplicationQueue and use the percolateUp() method to
   * maintain min-heap invariant of ApplicationQueue. Application should be compared using the
   * Application.compareTo() method.
   * 
   * 
   * @param o Application to add to this ApplicationQueue
   * @throws NullPointerException  if the given Application is null
   * @throws IllegalStateException with a descriptive error message if this ApplicationQueue is full
   */
  @Override
  public void enqueue(Application o) throws NullPointerException, IllegalStateException {
    // TODO verify the application
    if (o == null) {
      throw new NullPointerException("the given Application is null");
    }
    // TODO verify that the queue is not full
    if (queue.length <= size) {
      throw new IllegalStateException("the ApplicationQueue is full");
    }
    // TODO if allowed, add the application to the queue and percolate to restore the heap condition
    queue[size] = o;
    size++;
    percolateUp(size - 1);
  }

  /**
   * Removes and returns the Application at the root of this ApplicationQueue, i.e. the Application
   * with the lowest score.
   * 
   * @return the Application in this ApplicationQueue with the smallest score
   * @throws NoSuchElementException with a descriptive error message if this ApplicationQueue is
   *                                empty
   */
  public Application dequeue() throws NoSuchElementException {
    // if (queue == null) { // if the queue is null, throw an exception
    // throw new NoSuchElementException("The queue is empty so no element can be removed");
    // }
    // Application lowestScoringApplication = queue[0];
    // // set the lowest scoring Application to the root of the queue
    // queue[0] = queue[size - 1];
    // // set the root of the queue to the last element of the queue
    // percolateDown(0); // use the percolateDown() method to maintain min-heap invariant
    //
    // return lowestScoringApplication; // return the lowest scoring Application

    if (this.isEmpty())
      throw new NoSuchElementException("AssignmentQueue is empty");
    Application applicationRemoved = queue[0];
    queue[0] = queue[size - 1];
    queue[size - 1] = null;
    size--;
    if (!this.isEmpty())
      this.percolateDown(0);

    return applicationRemoved;
  }

  /**
   * An implementation of percolateDown() method. Restores the min-heap invariant of a given subtree
   * by percolating its root down the tree. If the element at the given index does not violate the
   * min-heap invariant (it is due before its children), then this method does not modify the heap.
   * Otherwise, if there is a heap violation, then swap the element with the correct child and
   * continue percolating the element down the heap.
   * 
   * This method may be implemented recursively OR iteratively.
   * 
   * @param i index of the element in the heap to percolate downwards
   * @throws IndexOutOfBoundsException if index is out of bounds - do not catch the exception
   */
  private void percolateDown(int i) {
    int j = 2 * i + 1;
    int k = 2 * i + 2;
    if (j > size - 1 || k > size - 1) {

      return;
    } else {
      int leftChildCompareVal = queue[i].compareTo(queue[j]);
      int rightChildCompareVal = queue[i].compareTo(queue[k]);
      if (leftChildCompareVal > 0 && rightChildCompareVal > 0) {
        if (leftChildCompareVal > rightChildCompareVal) {
          Application tempApplication = queue[k];
          queue[k] = queue[i];
          queue[i] = tempApplication;
          percolateDown(k);
        } else if (rightChildCompareVal > leftChildCompareVal) {
          Application tempApplication = queue[j];
          queue[j] = queue[i];
          queue[i] = tempApplication;
          percolateDown(j);
        } else {
          Application tempApplication = queue[k];
          queue[k] = queue[i];
          queue[i] = tempApplication;
          percolateDown(k);
        }
      } else if (leftChildCompareVal > 0) {
        Application tempApplication = queue[j];
        queue[j] = queue[i];
        queue[i] = tempApplication;
        percolateDown(j);
      } else if (rightChildCompareVal > 0) {
        Application tempApplication = queue[k];
        queue[k] = queue[i];
        queue[i] = tempApplication;
        percolateDown(k);
      }
    }
  }

  /**
   * An implementation of percolateUp() method. Restores the min-heap invariant of the tree by
   * percolating a leaf up the tree. If the element at the given index does not violate the min-heap
   * invariant (it occurs after its parent), then this method does not modify the heap. Otherwise,
   * if there is a heap violation, swap the element with its parent and continue percolating the
   * element up the heap.
   * 
   * This method may be implemented recursively OR iteratively.
   * 
   * Feel free to add private helper methods if you need them.
   * 
   * @param i index of the element in the heap to percolate upwards
   * @throws IndexOutOfBoundsException if index is out of bounds - do not catch the exception
   */
  private void percolateUp(int i) {

    if (i >= size || i >= queue.length || i < 0)
      throw new IndexOutOfBoundsException();

    if (i != 0 && queue[(i - 1) / 2].compareTo(queue[i]) > 0) {
      Application temp = queue[(i - 1) / 2];
      queue[(i - 1) / 2] = queue[i];
      queue[i] = temp;
      this.percolateUp((i - 1) / 2);
    }
  }
  // TODO implement the min-heap percolate up algorithm to modify the heap in place



  /**
   * Returns the Application at the root of this ApplicationQueue, i.e. the Application with the
   * lowest score.
   * 
   * @return the Application in this ApplicationQueue with the smallest score
   * @throws NoSuchElementException if this ApplicationQueue is empty
   */
  @Override
  public Application peek() {
    // TODO verify that the queue is not empty
    if (isEmpty()) {// is empty or null check
      throw new NoSuchElementException("The ApplicationQueue is empty");
    }

    // TODO return the lowest-scoring application
    return queue[0]; // TODO fix
  }

  /**
   * Returns a deep copy of this ApplicationQueue containing all of its elements in the same order.
   * This method does not return the deepest copy, meaning that you do not need to duplicate
   * applications. Only the instance of the heap (including the array and its size) will be
   * duplicated.
   * 
   * @return a deep copy of this ApplicationQueue. The returned new application queue has the same
   *         length and size as this queue.
   */
  public ApplicationQueue deepCopy() {
  ApplicationQueue output = new ApplicationQueue(queue.length);
  for (Application a : queue)
      if (a != null)
          output.enqueue(a);
  return output;
}

  /**
   * Returns a String representing this ApplicationQueue, where each element (application) of the
   * queue is listed on a separate line, in order from the lowest score to the highest score.
   * 
   * This implementation is provided.
   * 
   * @see Application#toString()
   * @see ApplicationIterator
   * @return a String representing this ApplicationQueue
   */
  @Override
  public String toString() {
    StringBuilder val = new StringBuilder();

    for (Application a : this) {
      val.append(a).append("\n");
    }

    return val.toString();
  }

  /**
   * Returns an Iterator for this ApplicationQueue which proceeds from the lowest-scored to the
   * highest-scored Application in the queue.
   * 
   * This implementation is provided.
   * 
   * @see ApplicationIterator
   * @return an Iterator for this ApplicationQueue
   */
  @Override
  public Iterator<Application> iterator() {
    return new ApplicationIterator(this);
  }
}
