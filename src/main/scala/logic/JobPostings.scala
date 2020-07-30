package logic

object JobPostings {

  var jobPostings = scala.collection.mutable.Map.empty[Long, TimedAction]

  def addOrRemove(action: TimedAction): Unit = {
    action.accountAction match {
      case AddJob => jobPostings += (action.jobId -> action)
      case RemoveJob => jobPostings.remove(action.jobId)
      case _ =>
    }
  }

}
