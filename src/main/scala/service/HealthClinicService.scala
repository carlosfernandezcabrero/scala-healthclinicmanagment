package scala.udemy.healthclinicmanagment
package service

import model.{CSVEntity, HealthClinic}
import resource.FileUtils

import scala.collection.mutable.ListBuffer

object HealthClinicService {

  val path_file = "src/main/resources/Health_clinics.csv"

  /**
   * Method that loads all Health Clinics present in the file
   * @return ListBuffer[HealthClinic]
   */
  def loadHealthClinics(): ListBuffer[HealthClinic] = {
    val buffer = new ListBuffer[HealthClinic]

    FileUtils.readFile(path_file).foreach(line => {
      if (line.trim.nonEmpty) {
        val parts = line.split(",")
        buffer += HealthClinic(parts(0).trim, parts(1).trim, parts(2).trim)
      }
    })

    buffer
  }

  /**
   * Method that calls the CSV file writer
   * @param list list of objects that extends of trait CSVEntity
   */
  def syncWithFile(list: List[CSVEntity]): Unit = FileUtils.writeCSVFile(path_file, list)

}
