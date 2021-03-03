package scala.udemy.healthclinicmanagment

import exception.InvalidDniException
import model.{HealthClinic, Patient}
import resource.ApplicationContext
import service.PatientService

import scala.io.StdIn.readLine
import scala.util.control.Breaks.{break, breakable}

object Main {

  /**
   * Method that displays the second level of the options
   * @param healthClinicCode String
   */
  def menu2(healthClinicCode: String): Unit = {
    var option = 1

    breakable {
      do {
        println()
        println("Actions:")
        println("1. List patients")
        println("2. Add patient")
        println("3. Remove patient")
        println("4. Search patient by DNI")
        println("0. Back")

        try {
          option = readLine().toInt
          println()

          option match {
            case 1 =>
              PatientService.displayPatients(PatientService.listPatients(healthClinicCode))
            case 2 =>
              print("Room: ")
              val room = readLine().trim
              print("DNI: ")
              val dni = readLine().trim
              print("Sanitary code: ")
              val sanitaryCode = readLine().trim
              print("Name: ")
              val name = readLine().trim
              print("Surnames: ")
              val surnames = readLine().trim
              println()

              try {
                if (PatientService.addPatient(Patient(room, dni, sanitaryCode, healthClinicCode, name, surnames))) {
                  println("The patient is added successfully")
                }
              } catch {
                case e: InvalidDniException =>
                  println(e.getMessage)
              }
            case 3 =>
              val removePatient = PatientService.removePatient(healthClinicCode) _
              val patients = PatientService.listPatients(healthClinicCode)

              println("Type the DNI of patient")
              PatientService.displayPatients(patients)

              if (patients.nonEmpty) {
                if (removePatient(readLine().trim)) println("Remove successfully") else println("Not found a patient with DNI specified")
              }
            case 4 =>
              print("Type DNI: ")
              println(PatientService.findPatientByDni(readLine().trim).getOrElse("Not found any patient with DNI"))
            case 0 =>
              break
          }
        } catch {
          case _: NumberFormatException =>
            println("The option isn't correct")
        }
      } while (option != 0)
    }
  }

  /**
   * Method that displays the first level of the options
   */
  def menu1(): Unit = {
    var option = 1
    val healthClinics = ApplicationContext.healthClinics
    var healthClinic: Option[HealthClinic] = None

    breakable {
      do {
        println("Health clinics:")
        for (i <- healthClinics.indices) {
          healthClinic = Some(healthClinics(i))
          println((i + 1) + ". " + healthClinic.get.name + ", " + healthClinic.get.address)
        }
        println("0. EXIT")

        try {
          option = readLine().toInt

          option match {
            case 0 =>
              break
            case _ =>
              menu2(healthClinics(option - 1).code)
          }
        } catch {
          case _: NumberFormatException =>
            println("The option isn't correct\n")
        }
      } while (option != 0)
    }
    println("*** Program completed successfully ***")
  }

  def main(args: Array[String]): Unit = {
    try {
      ApplicationContext.init()
      println()
      menu1()
      ApplicationContext.syncWithFiles()
    } catch {
      case _: InvalidDniException =>
        println("ERROR: Found patients with errors in the field 'dni'")
    }
  }

}
