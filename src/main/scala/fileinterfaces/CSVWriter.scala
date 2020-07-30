package fileinterfaces

import java.io.{File, PrintWriter}

sealed class CSVWriter(file:File) extends Writer[List[String]]{
  private val writer = new PrintWriter(file)
  private val separator = ","

  def writeHeader(header:List[String]) = writer.write(header.mkString(separator))

  def write(data:List[String]) = writer.write("\n"+data.mkString(separator))

  def close = writer.close()
}
