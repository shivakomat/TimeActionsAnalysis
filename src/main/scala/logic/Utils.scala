package logic

import org.joda.time.DateTime
import org.joda.time.format.DateTimeFormat

object Utils {
  def toDateTime(data: String): DateTime = {
    val sourceFormat = DateTimeFormat.forPattern("YYYY-MM-DD HH:mm:SS")
    DateTime.parse(data, sourceFormat)
  }
}
