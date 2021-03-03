package scala.udemy.healthclinicmanagment
package model

class Patient (
                val room: String,
                override val dni: String,
                val sanitaryCode: String,
                val healthClinicCode: String,
                override val name: String,
                override val surnames: String
) extends Person (name, surnames, dni) with CSVEntity {

  override def toString: String = {
    s"""Full name: $name $surnames
       |DNI: $dni Sanitary code: $sanitaryCode Room: $room
       |""".stripMargin
  }

  val toCSV: String = room + "," + dni + "," + sanitaryCode + "," + healthClinicCode + "," + name + "," + surnames
}

object Patient {
  def apply(room: String, dni: String, sanitaryCode: String, healthClinicCode: String, name: String, surnames: String): Patient = {
    val patient = new Patient(room, dni, sanitaryCode, healthClinicCode, name, surnames)
    patient.checkDni()
    patient
  }
}
