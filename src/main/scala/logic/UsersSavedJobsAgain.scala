package logic

object UsersSavedJobsAgain {

  private var userSavedJobs = scala.collection.mutable.Seq.empty[TimedAction]
  private var userUnsavedJobs = scala.collection.mutable.Seq.empty[TimedAction]
  var userSavedJobsAgain = scala.collection.mutable.Seq.empty[TimedAction]

  def addTo(action: TimedAction): Unit = {
    if(action.accountAction == SaveJob && userUnsavedJobs.exists(j => j.jobId == action.jobId))
      userSavedJobsAgain = userSavedJobsAgain ++ Seq(action)
    else if(action.accountAction == SaveJob)
      userSavedJobs = userSavedJobs ++ Seq(action)
    else if(action.accountAction == UnSaveJob && userSavedJobs.exists(j => j.jobId == action.jobId)
      && !userUnsavedJobs.exists(j => j.jobId == action.jobId))
      userUnsavedJobs = userUnsavedJobs ++ Seq(action)
  }

}
