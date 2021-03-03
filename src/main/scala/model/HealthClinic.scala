package scala.udemy.healthclinicmanagment
package model

class HealthClinic (
  val name: String,
  val address: String,
  val code: String
  ) extends CSVEntity {

  val toCSV: String = name + "," + address + "," + code
}

object HealthClinic {
  def apply(name: String, address: String, code: String): HealthClinic =
    new HealthClinic(name, address, code)
}
