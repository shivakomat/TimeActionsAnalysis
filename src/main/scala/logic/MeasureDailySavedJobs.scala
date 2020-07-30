package logic

object MeasureDailySavedJobs {

  var dailyFreqSavedJobs: Map[Long, Int] = Map()

  def addTo(action: TimedAction): Unit = {
    if(action.accountAction == "SAVE_JOB") {
      val dateInLong = (action.timeStamp.getYear + "" + action.timeStamp.getMonthOfYear + "" + action.timeStamp.getDayOfMonth).toLong
      if(dailyFreqSavedJobs.get(dateInLong).nonEmpty) dailyFreqSavedJobs += (dateInLong -> ((dailyFreqSavedJobs(dateInLong) + 1)))
      else dailyFreqSavedJobs += (dateInLong -> 1)
    }
  }

  def twoDaysThatHadTheMostIncreaseInSavedJobs(): (Long, Long) = {
    val sortedDailyFreqSavedJobs =  dailyFreqSavedJobs.toSeq.sortBy(_._1)
    val freqWithDeltas =
      sortedDailyFreqSavedJobs.zipWithIndex.map(j => {
        if(j._2 != 0) {
          val prevCount = sortedDailyFreqSavedJobs(j._2 - 1)._2
          val currCount = j._1._2
          (j._1._1 -> (j._1._2, (currCount - prevCount)))
        } else (j._1._1 -> (j._1._2, 0))
      })
    val topMost = freqWithDeltas.sortBy(_._2._2)(Ordering.Int.reverse)
    (topMost.head._1, topMost.drop(1).head._1)
  }
}
