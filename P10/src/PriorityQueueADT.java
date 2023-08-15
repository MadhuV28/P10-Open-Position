/////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:PriorityQueueADT
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
/**
 * This interface models the priority queue abstract data type
 *
 * @author Madhu Vuyyuru
 * @param <T> type parameter bounded by the Comparable interface. Represents the type of elements
 *            stored in this priority queue.
 */
import java.util.NoSuchElementException;

public interface PriorityQueueADT<T extends Comparable<T>> {
  /**
   * Checks if this priority queue is empty.
   *
   * @return true if this priority queue is empty and false otherwise.
   */
  public boolean isEmpty();

  /**
   * Returns the current size of this priority queue.
   *
   * @return the size of this priority queue
   */
  public int size();

  /**
   * Adds the given element to the priority queue
   *
   * @param element to be added to this queue
   * @throws NullPointerException  if element is null
   * @throws IllegalStateException with a descriptive error message if this priority queue is full
   */
  public void enqueue(T element);

  /**
   * Returns and removes the element at the front (aka root position) of this queue (the element
   * having the highest priority).
   *
   * @return the removed element
   * @throws NoSuchElementException with a descriptive error message if this queue is empty
   */
  public T dequeue();

  /**
   * Returns without removing the element at the front (aka root position) of this queue (the
   * element having the highest priority).
   *
   * @return the element with the highest priority in this queue
   * @throws NoSuchElementException with a descriptive error message if this queue is empty
   */
  public T peek();
}
