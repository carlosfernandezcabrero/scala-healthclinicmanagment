package scala.udemy.healthclinicmanagment
package exception

final class InvalidDniException(message: String, cause: Throwable = None.orNull) extends Exception (message, cause)
