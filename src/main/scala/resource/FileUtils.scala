package scala.udemy.healthclinicmanagment
package resource

import model.CSVEntity

import java.io.{BufferedWriter, File, FileNotFoundException, FileWriter}
import scala.collection.mutable.ListBuffer
import scala.io.Source

object FileUtils {

  /**
   * Method that reads a file
   * @param path String
   * @return List[String]
   */
  def readFile(path: String): List[String] = {
    var res = List[String]()
    var file = None: Option[Source]

    try {
      file = Some(Source.fromFile(path))
      res = file.get.getLines().toList
    } catch {
      case _: FileNotFoundException =>
        println("Creating file " + path + " ...")
        this.writeFile(path, List.empty)
    } finally {
      println("Loading file " + path + " ...")
      if (file.isDefined) {
        file.get.close()
      }
    }

    res
  }

  /**
   * Method that write a file the content present in the list param
   * @param path String
   * @param list of String
   */
  private def writeFile(path: String, list: List[String]): Unit = {
    val bufferedWriter = new BufferedWriter(new FileWriter(new File(path)))
    list.foreach(i => bufferedWriter.write(i + "\n"))
    bufferedWriter.close()
  }

  /**
   * Method that write a file the content present in the list param
   * @param path String
   * @param list of CSVEntity
   */
  def writeCSVFile(path: String, list: List[CSVEntity]): Unit = {
    val listString = new ListBuffer[String]

    list.foreach(listString += _.toCSV)

    this.writeFile(path, listString.toList)
  }

}
