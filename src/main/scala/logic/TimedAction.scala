package logic

import org.joda.time.DateTime

case class TimedAction(timeStamp: DateTime, accountType: AccountType, accountAction: String, accountId: String, jobId: Long)

trait AccountType
case object Employer extends AccountType
case object User extends AccountType
case object Unknown extends AccountType

object TimedAction {

  def apply(row: String): TimedAction = {
    val rowWithColumns = row.split(",")
    new TimedAction(timeStamp = Utils.toDateTime(rowWithColumns(0)),
                    accountType = convertToObject(rowWithColumns(1)),
                    accountAction = rowWithColumns(2),
                    accountId = rowWithColumns(3),
                    jobId = rowWithColumns(4).toLong)
  }

  private def convertToObject(accountType: String): AccountType =
    accountType match {
      case "EMPLOYER" => Employer
      case "USER" => User
      case _ => Unknown
    }

}