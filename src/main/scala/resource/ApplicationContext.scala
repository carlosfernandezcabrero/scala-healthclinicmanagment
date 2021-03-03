package scala.udemy.healthclinicmanagment
package resource

import service.{HealthClinicService, PatientService}
import model.{HealthClinic, Patient}

import scala.collection.mutable.ListBuffer

object ApplicationContext {

  var healthClinics: ListBuffer[HealthClinic] = _
  var patients: ListBuffer[Patient] = _

  /**
   * Method that loads all the necessary data
   */
  def init(): Unit ={
    healthClinics = HealthClinicService.loadHealthClinics()
    patients = PatientService.loadPatients()
  }

  /**
   * Method that writes all the data present in the application to the files
   */
  def syncWithFiles(): Unit = {
    PatientService.syncWithFile(patients.toList)
  }

  /**
   * Method that performs all the required operations when the application is exited
   */
  def close(): Unit = {
    syncWithFiles()
  }

}
