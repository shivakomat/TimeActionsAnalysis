package fileinterfaces

import scala.io.{BufferedSource, Source}

sealed class FileHandler(fileUrl: String) {

  val buffer: BufferedSource = Source.fromFile(fileUrl)

  def getStream: Iterator[String] = {
    buffer.getLines()
  }

  def closeHandler(): Unit =
    buffer.close()

}

object FileHandler {

  def apply(fileUrl: String): FileHandler = {
    new FileHandler(fileUrl: String)
  }
}