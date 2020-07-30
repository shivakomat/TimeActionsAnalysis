package logic

import org.joda.time.DateTime

case class TimedAction(timeStamp: DateTime, accountType: AccountType, accountAction: AccountAction, accountId: String, jobId: Long)

trait AccountType
case object Employer extends AccountType
case object User extends AccountType
case object UnknownType extends AccountType

trait AccountAction
case object AddJob extends AccountAction
case object SaveJob extends AccountAction
case object RemoveJob extends AccountAction
case object UnSaveJob extends AccountAction
case object UnknownAction extends AccountAction

object TimedAction {

  def apply(row: String): TimedAction = {
    val rowWithColumns = row.split(",")
    new TimedAction(timeStamp = Utils.toDateTime(rowWithColumns(0)),
                    accountType = convertToObject(rowWithColumns(1)),
                    accountAction = convertAccountActionToObject(rowWithColumns(2)),
                    accountId = rowWithColumns(3),
                    jobId = rowWithColumns(4).toLong)
  }

  private def convertToObject(accountType: String): AccountType =
    accountType.toUpperCase match {
      case "EMPLOYER" => Employer
      case "USER" => User
      case _ => UnknownType
    }

  private def convertAccountActionToObject(accountAction: String): AccountAction =
    accountAction.toUpperCase match {
      case "ADD_JOB" => AddJob
      case "SAVE_JOB" => SaveJob
      case "REMOVE_JOB" => RemoveJob
      case "UN_SAVE_JOB" => UnSaveJob
      case _ => UnknownAction
    }

}