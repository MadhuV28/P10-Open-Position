/////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: Application
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
 * This class models a application with a name and due date and implements Comparable
 * 
 * @author mvuyyuru
 *
 */
public class Application implements Comparable<Application> {
  private final String name; // name of this applicant
  private final String email; // email of this applicant
  private final int score; // estimated score of this applicant

  /**
   * Creates a new Application with the given information
   *
   * @param name  name of this applicant
   * @param email email of this applicant
   * @param score estimated score of this applicant (must be in the range 0 .. 100)
   * @throws IllegalArgumentException if the provided name is null or blank, or if the email is null
   *                                  or does not have a single {@literal @}, or if score is not in
   *                                  the 0 .. 100 range.
   */
  public Application(String name, String email, int score) throws IllegalArgumentException {
    // throws an IllegalArgumentException if the provided name is null or blank
    if (name == null || name == "") {
      // if the name is null or blank throw an IllegalArgumentException
      throw new IllegalArgumentException("The name cannot be null or Empty String");
    }
    if (email == null || email == "") {
      // if the email is null or blank throw an IllegalArgumentException
      throw new IllegalArgumentException("The email cannot be null or Empty String");
    }
    if (email.indexOf("@") == -1) {
      // if the email does not have a single @ throw an IllegalArgumentException
      throw new IllegalArgumentException("The email is invalid");
    }
    if (email.indexOf("@") != email.lastIndexOf("@")) {
      // if the email has more than one @ throw an IllegalArgumentException
      throw new IllegalArgumentException("The email is invalid");
    }
    if (score < 0 || score > 100) {
      // if the score is not in the o to 100 range throw an IllegalArgumentException
      throw new IllegalArgumentException("The score is invalid");
    }
    this.name = name; // set the name of this applicant
    this.email = email; // set the email of this applicant
    this.score = score; // set the score of this applicant
  }

  /**
   * Returns the name of this Applicant
   * 
   * @return the name of this Applicant
   */
  public String getName() {
    return this.name; // TODO fix
  }

  /**
   * Returns the email of this Applicant
   * 
   * @return the email of this Applicant
   */
  public String getEmail() {
    return this.email; // TODO fix
  }

  /**
   * Returns the score of this Applicant
   * 
   * @return the score of this Applicant
   */
  public int getScore() {
    return this.score; // TODO fix
  }

  /**
   * TODO: add this method Compares this Applicant to another applicant based on their score
   * 
   * @return a negative integer if this Applicant has an lower score, {@code 0} if the two
   *         Applicants have the same score, and a positive integer if this Applicant has a higher
   *         score.
   * @throws NullPointerException if the other assignment o is null
   */
  @Override
  public int compareTo(Application other) throws NullPointerException {
    if (other == null) { // if the other applicant is null throw an NullPointerException
      throw new NullPointerException("The other application should not be zero");
    }
    return this.score - other.score; // return the difference between the scores
  }

  /**
   * Returns a String representing this Application containing its name, email and score. (This
   * implementation is provided for you.)
   * 
   * @return a String representing this Application
   */
  @Override
  public String toString() {
    return name + ":" + email + ":" + score;
  }
}
