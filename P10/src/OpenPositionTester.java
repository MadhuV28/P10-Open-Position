/////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: OpenPositionTester
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
import java.util.NoSuchElementException;

/**
 * This class implements unit test methods to check the correctness of Application,
 * ApplicationIterator, ApplicationQueue and OpenPosition classes in the assignment.
 *
 */
public class OpenPositionTester {

  /**
   * This method tests and makes use of the Application constructor, getter methods, toString() and
   * compareTo() methods.
   * 
   * @return true when this test verifies the functionality, and false otherwise
   */
  public static boolean testApplication() {
    // create an Application with valid input
    try {
      Application valid = new Application("John", "john@gmail.com", 100);
    } catch (Exception e) {
      return false;
    }

    // create an Application with invalid input:
    // blank name
    try {
      Application blankName = new Application("", "john@gmail.com", 98);
      return false;
    } catch (IllegalArgumentException iae) {
      System.out.println(iae.getMessage());
    } catch (Exception e) {
      return false;
    }

    // null email
    try {
      Application nullEmail = new Application("John", null, 98);
      return false;
    } catch (IllegalArgumentException iae) {
      System.out.println(iae.getMessage());
    } catch (Exception e) {
      return false;
    }

    // no @ email
    try {
      Application noAtEmail = new Application("John", "johngmail.com", 98);
      return false;
    } catch (IllegalArgumentException iae) {
      System.out.println(iae.getMessage());
    } catch (Exception e) {
      return false;
    }

    // too many @ email
    try {
      Application tooManyAtEmail = new Application("John", "john@gm@il.com", 98);
      return false;
    } catch (IllegalArgumentException iae) {
      System.out.println(iae.getMessage());
    } catch (Exception e) {
      return false;
    }

    // invalid score
    try {
      Application invalidScore = new Application("John", "john@gmail.com", 101);
      return false;
    } catch (IllegalArgumentException iae) {
      System.out.println(iae.getMessage());
    } catch (Exception e) {
      return false;
    }
    // verify getters
    Application getter = new Application("John", "john@gmail.com", 98);
    if (!getter.getName().equals("John")) {
      return false;
    }

    if (!getter.getEmail().equals("john@gmail.com")) {
      return false;
    }
    if (getter.getScore() != 98) {
      return false;
    }
    // verify compareTo
    Application compareEqual = new Application("John", "john@gmail.com", 98);
    Application compareGreater = new Application("John", "john@gmail.com", 97);
    Application compareLess = new Application("John", "john@gmail.com", 99);

    if (getter.compareTo(compareLess) != -1) {

      return false;
    }

    if (getter.compareTo(compareEqual) != 0) {
      return false;
    }
    if (getter.compareTo(compareGreater) != 1) {
      return false;
    }
    // verify toString
    if (!getter.toString().equals("John:john@gmail.com:98")) {
      return false;
    }
    return true;
  }

  /**
   * This method tests and makes use of the ApplicationIterator class.
   * 
   * @return true when this test verifies the functionality, and false otherwise
   */
  public static boolean testApplicationIterator() {
    // create an ApplicationQueue with capacity at least 3
    // and at least 3 Applications with different scores
    ApplicationQueue test = new ApplicationQueue(3);

    Application jefferey = new Application("jefferey", "john@gmail.com", 99);
    Application joe = new Application("Joe", "joe@gmail.com", 82);
    Application jeff = new Application("Jeff", "jeff@gmail.com", 70);
    // add those Applications to the queue
    test.enqueue(jefferey);
    test.enqueue(joe);
    test.enqueue(jeff);
    ApplicationIterator testIterator = new ApplicationIterator(test);
    // verify that iterating through the queue gives you the applications in order of
    // INCREASING score
    int score1 = testIterator.next().getScore();
    int score2 = testIterator.next().getScore();
    int score3 = testIterator.next().getScore();
    if (score1 != 73 || score2 != 85 || score3 != 99) {
      return false;
    }
    return true;
  }

  /**
   * This method tests and makes use of the enqueue() and dequeue() methods in the ApplicationQueue
   * class.
   * 
   * @return true when this test verifies the functionality, and false otherwise
   */
  public static boolean testEnqueueDequeue() {
    ApplicationQueue test = new ApplicationQueue(3);

    Application john = new Application("John", "john@gmail.com", 99);
    Application joe = new Application("Joe", "joe@gmail.com", 82);
    Application jeff = new Application("Jeff", "jeff@gmail.com", 70);
    Application james = new Application("James", "james@gmail.com", 62);

    // enqueue an invalid value (null)
    try {
      test.enqueue(null);
      return false;
    } catch (NullPointerException npe) {
      System.out.println(npe.getMessage());
    } catch (Exception e) {
      return false;
    }

    // enqueue one valid application
    try {
      test.enqueue(jeff);
      if (test.peek().getScore() != 73) {
        return false;
      }
      // enqueue two more valid applications

      test.enqueue(james);
      if (test.peek().getScore() != 62) {
        return false;
      }
      test.enqueue(joe);
      if (test.peek().getScore() != 62) {
        System.out.println(1);
        return false;
      }
    } catch (Exception e) {
      return false;
    }
    if (test.size() != 3) {
      return false;
    }

    // enqueue one more application (exceeds capacity)
    try {
      test.enqueue(john);
      return false;
    } catch (IllegalStateException ise) {
      System.out.println(ise.getMessage());
    } catch (Exception e) {
      return false;
    }
    if (test.size() != 3) {
      return false;
    }

    // dequeue one application (should be lowest score)
    if (test.dequeue().getScore() != 62) {
      return false;
    }

    // dequeue all applications
    test.dequeue();
    test.dequeue();

    // dequeue from an empty queue
    try {
      test.dequeue();
      return false;
    } catch (NoSuchElementException nsee) {
      System.out.println(nsee.getMessage());
    } catch (Exception e) {
      return false;
    }
    return true;
  }

  /**
   * This method tests and makes use of the common methods (isEmpty(), size(), peek()) in the
   * ApplicationQueue class.
   * 
   * @return true when this test verifies the functionality, and false otherwise
   */
  public static boolean testCommonMethods() {
    // create an ApplicationQueue with 0 capacity (should fail)
    try {
      ApplicationQueue empty = new ApplicationQueue(0);
      return false;
    } catch (IllegalArgumentException iae) {
      System.out.println(iae.getMessage());
    } catch (Exception e) {
      return false;
    }
    // create an ApplicationQueue with capacity 3
    // and at least 3 Applications with different scores
    ApplicationQueue test = new ApplicationQueue(3);
    Application john = new Application("John", "john@gmail.com", 99);
    Application joe = new Application("Joe", "joe@gmail.com", 85);
    Application jeff = new Application("Jeff", "jeff@gmail.com", 73);
    // verify the methods' behaviors on an empty queue
    if (!test.isEmpty()) {
      return false;
    }
    if (test.size() != 0) {
      return false;
    }
    try {
      test.peek();
      return false;
    } catch (NoSuchElementException nsee) {
      System.out.println(nsee.getMessage());
    } catch (Exception e) {
      return false;
    }

    // add one Application and verify the methods' behaviors
    test.enqueue(john);
    if (test.isEmpty()) {
      return false;
    }
    if (test.size() != 1) {
      return false;
    }
    if (test.peek() != john) {
      return false;
    }
    // add the rest of the Applications and verify the methods' behaviors
    test.enqueue(joe);
    test.enqueue(jeff);
    if (test.isEmpty()) {
      return false;
    }
    if (test.size() != 3) {
      return false;
    }
    if (test.peek() != jeff) {

      return false;
    }
    return true;
  }

  /**
   * This method tests and makes use of OpenPosition class.
   * 
   * @return true when this test verifies the functionality, and false otherwise
   */
  public static boolean testOpenPosition() {
    // create an OpenPosition with 0 capacity (should fail)
    try {
      OpenPosition openTest = new OpenPosition("TA", 0);
      return false;
    } catch (IllegalArgumentException iae) {
      System.out.println(iae.getMessage());
    } catch (Exception e) {
      return false;
    }

    // create an OpenPosition with capacity 3
    // and at least 5 Applications with different scores
    OpenPosition openTest = new OpenPosition("TA", 3);
    Application john = new Application("John", "john@gmail.com", 99);
    Application joe = new Application("Joe", "joe@gmail.com", 85);
    Application jeff = new Application("Jeff", "jeff@gmail.com", 73);
    Application james = new Application("James", "james@gmail.com", 62);
    Application jay = new Application("Jay", "jay@gmail.com", 57);

    // verify that the 3 MIDDLE-scoring Applications can be added
    // don't use the highest and lowest scoring applications YET
    try {
      System.out.println(openTest.add(joe));
      System.out.println(openTest.add(jeff));
      System.out.println(openTest.add(james));
    } catch (Exception e) {
      return false;
    }

    // verify that getApplications returns the correct value for your input
    if (!openTest.getApplications().trim().equals("James:james@gmail.com:62" + "\n"
        + "Jeff:jeff@gmail.com:73" + "\n" + "Joe:joe@gmail.com:85")) {
      return false;
    }

    // verify that the result of getTotalScore is the sum of all 3 Application scores
    if (openTest.getTotalScore() != 220) {

      return false;
    }

    // verify that the lowest-scoring application is NOT added to the OpenPosition
    if (openTest.add(jay)) {

      return false;
    }

    // verify that the highest-scoring application IS added to the OpenPosition
    if (!openTest.add(john)) {

      return false;
    }

    // verify that getApplications has changed correctly
    if (!openTest.getApplications().trim().equals("Jeff:jeff@gmail.com:73" + "\n"
        + "Joe:joe@gmail.com:85" + "\n" + "John:john@gmail.com:99")) {

      return false;
    }

    // verify that the result of getTotalScore has changed correctly
    if (openTest.getTotalScore() != 257) {
      System.out.println(openTest.getTotalScore());
      return false;
    }
    return true;
  }

  /**
   * This method calls all the test methods defined and implemented in your OpenPositionTester
   * class.
   * 
   * @return true if all the test methods defined in this class pass, and false otherwise.
   */
  public static boolean runAllTests() {
    return testOpenPosition();
  }

  /**
   * Driver method defined in this OpenPositionTester class
   * 
   * @param args input arguments if any.
   */
  public static void main(String[] args) {
    System.out.print(runAllTests());
  }
}


