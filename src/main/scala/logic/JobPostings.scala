package logic

object JobPostings {

  var jobPostings = scala.collection.mutable.Map.empty[Long, TimedAction]

  def addOrRemove(action: TimedAction): Unit = {
    if(action.accountAction == "ADD_JOB") jobPostings += (action.jobId -> action)
    else if(action.accountAction == "REMOVE_JOB") {
      jobPostings.remove(action.jobId)
    }
  }

}
