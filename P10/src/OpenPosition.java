/////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: OpenPosition
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
 * A application handler of an open position using priority queue. Only saves a new Application when
 * the queue is not full, or when it can replace older, lower-scored ones with its higher scores.
 * 
 * @author Madhu Vuyyuru
 */
public class OpenPosition {
  private String positionName;
  private ApplicationQueue applications; // the priority queue of all applications
  private int capacity; // the number of vacancies

  /**
   * Creates a new open position with the given capacity
   * 
   * @param capacity the number of vacancies of this position
   * @throws IllegalArgumentException with a descriptive error message if the capacity is not a
   *                                  positive integer
   */
  public OpenPosition(String positionName, int capacity) {
    if (capacity <= 0) {
      throw new IllegalArgumentException("The capacity can not be less than 0");
    }
    this.applications = new ApplicationQueue(capacity);
    this.positionName = positionName;
    this.capacity = capacity;
    // TODO initialize the data fields appropriately
  }

  public String getPositionName() {
    return this.positionName;
  }

  /**
   * Tries to add the given Application to the priority queue of this position. return False when
   * the new Application has a lower score than the lowest-scored Application in the queue.
   * 
   * @return Whether the given Application was added successfully
   */
  public boolean add(Application application) {
    // TODO if the queue is full, determine whether this application has a higher score than
    // the current lowest-scoring application; if not, do not add it
    if (capacity == 0) { // if the queue is full return false
      if (applications.peek().compareTo(application) >= 0) {
        // if the application has a lower score than the lowest-scoring application
        // return false
        return false;
      } else {
        // if the application has a higher score than the lowest-scoring application add it
        applications.dequeue(); // remove the lowest-scoring application
        applications.enqueue(application); // add the new application
      }
    } else {
      applications.enqueue(application); // add the new application
    }

    return true;
  }
  // TODO if there is room in the queue OR this application has a higher score than the current
  // lowest-scoring application, add it to the queue


  /**
   * Returns the list of Applications in the priority queue.
   * 
   * @return The list of Applications in the priority queue, in increasing order of the scores.
   */
  public String getApplications() {
    return applications.toString();
  }

  /**
   * Returns the total score of Applications in the priority queue.
   * 
   * @return The total score of Applications in the priority queue.
   */
  public int getTotalScore() {


    int totalScore = 0; // initialize the total score
    for (Application application : applications)
      totalScore += application.getScore();
    // add the score of each application


    return totalScore; // return the total score
  }

}
