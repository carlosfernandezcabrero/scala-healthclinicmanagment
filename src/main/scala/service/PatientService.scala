package scala.udemy.healthclinicmanagment
package service

import scala.collection.mutable.ListBuffer
import model.{CSVEntity, Patient}
import resource.{ApplicationContext, FileUtils}

object PatientService {

  val path_file = "src/main/resources/Patients.csv"

  /**
   * Method that loads all Patients present in the file
   * @return ListBuffer[Patient]
   */
  def loadPatients(): ListBuffer[Patient] = {
    val buffer = new ListBuffer[Patient]

    FileUtils.readFile(path_file).foreach(line => {
      if (line.trim.nonEmpty) {
        val parts = line.split(",")
        val room = parts(0).trim
        val dni = parts(1).trim
        val sanitaryCode = parts(2).trim
        val healthClinicCode = parts(3).trim
        val name = parts(4).trim
        val surnames = parts(5).trim
        buffer += Patient(room, dni, sanitaryCode, healthClinicCode, name, surnames)
      }
    })

    buffer
  }

  /**
   * Method that lists all patients of a Health Clinic
   * @param healthClinicCode String
   * @return List[Patient]
   */
  def listPatients(healthClinicCode: String): List[Patient] = ApplicationContext.patients.filter(_.healthClinicCode == healthClinicCode).toList

  /**
   * Method that adds a Patient
   * @param patient Patient object
   * @return Boolean
   */
  def addPatient(patient: Patient): Boolean = {
    ApplicationContext.patients += patient
    true
  }

  /**
   * Method that looks for a patient by their ID
   * @param dni String
   * @return Option[Patient]
   */
  def findPatientByDni(dni: String): Option[Patient] = ApplicationContext.patients.find(_.dni == dni)

  /**
   * Method that removes a patient
   * @param healthClinicCode String
   * @param dni String
   * @return Boolean
   */
  def removePatient(healthClinicCode: String)(dni: String): Boolean = {
    val patients = ApplicationContext.patients
    val isRemoved = {
      val patientOption = patients.find(p => (p.healthClinicCode == healthClinicCode) && (p.dni == dni))
      if (patientOption.isEmpty) false else {
        patients -= patientOption.get
        true
      }
    }

    isRemoved
  }

  /**
   * Method that displays all patients in the list
   * @param list of Patient
   */
  def displayPatients(list: List[Patient]): Unit = if (list.nonEmpty) list.foreach(println) else println("Not found any patient")

  /**
   * Method that calls the CSV file writer
   * @param list of CSVEntity
   */
  def syncWithFile(list: List[CSVEntity]): Unit = FileUtils.writeCSVFile(path_file, list)

}
