package scala.udemy.healthclinicmanagment
package model

import java.util.regex.Pattern
import exception.InvalidDniException

abstract class Person (
  val name: String,
  val surnames: String,
  val dni: String
) {

  /**
   * Method that checks if the dni is valid
   * @throws InvalidDniException When the dni isn't correct
   */
  def checkDni(): Unit = {
    val pat = Pattern.compile("[0-9]{7,8}[A-Za-z]")
    val mat = pat.matcher(dni)
    //val alphabet = List('T', 'R', 'W', 'A', 'G', 'M', 'Y', 'F', 'P', 'D', 'X', 'B', 'N', 'J', 'Z', 'S', 'Q', 'V', 'H',
    //    'L', 'C', 'K', 'E')
    //val letIntro = dni.charAt(dni.length - 1).toUpper
    //val numIntro = dni.substring(0, dni.length() - 1).toInt

    // Strict mode: if ((!mat.matches()) || (mat.matches() && letIntro != alphabet(numIntro % 23))){
    if (!mat.matches()) {
      throw new InvalidDniException("ERROR: The dni is invalid")
    }
  }
}
