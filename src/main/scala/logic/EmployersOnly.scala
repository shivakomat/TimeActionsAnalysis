package logic

import scala.collection.mutable.Seq

object EmployersOnly {

  var employerIdsOnly = scala.collection.mutable.Seq.empty[TimedAction]

  def addTo(action: TimedAction): Unit = {
    action.accountType match {
      case Employer => employerIdsOnly = employerIdsOnly ++ Seq(action)
      case _ =>
    }
  }

  def apply(action: TimedAction): Boolean =
    (action.accountType == Employer)

}
