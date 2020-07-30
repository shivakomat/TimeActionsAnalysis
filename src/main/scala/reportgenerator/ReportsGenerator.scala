package reportgenerator

import fileinterfaces.{CSVFileWriter, FileHandler}
import logic._

class ReportsGenerator(inputFile: String, outputDir: String) {

  def run(): Unit = {

    // output files
    val employerIdsOnlyFile = outputDir + "/employerIdsOnly.csv"
    val usersSavedJobsAgainFile = outputDir + "/userSavedJobsAgain.csv"
    val jobPostingsFile = outputDir + "/jobPostings.csv"
    val top2DaysHadTheMostIncreaseFile = outputDir + "/top2DaysHadTheMostIncreaseInSavedJobs.csv"

    val sourceFile = new FileHandler(inputFile)
    val rows = sourceFile.getStream
    val header = rows.take(1)
    val headerRow = 1

    rows.drop(headerRow).foreach(row => {
      val timedAction = TimedAction(row)
      EmployersOnly.addTo(timedAction)
      JobPostings.addOrRemove(timedAction)
      UsersSavedJobsAgain.addTo(timedAction)
      MeasureDailySavedJobs.addTo(timedAction)
      if(timedAction.accountType == UnknownType || timedAction.accountAction == UnknownAction) {
        println("Bad Data -> " + timedAction.toString)
      }
    })

    // Generate Outputs into Files

    val employerIdsOnly = CSVFileWriter(employerIdsOnlyFile)
    val userSavedJobsAgain = CSVFileWriter(usersSavedJobsAgainFile)
    val jobPostings = CSVFileWriter(jobPostingsFile)
    val top2DaysHadTheMostIncreaseInSavedJobs = CSVFileWriter(top2DaysHadTheMostIncreaseFile)
    try {
      EmployersOnly.employerIdsOnly.foreach(e => employerIdsOnly.write(List(e.accountId, e.accountType.toString, e.jobId.toString, e.accountAction.toString, e.timeStamp.toString)))
      UsersSavedJobsAgain.userSavedJobsAgain.foreach(s => userSavedJobsAgain.write(List(s.accountId, s.accountType.toString, s.jobId.toString, s.accountAction.toString, s.timeStamp.toString)))
      JobPostings.jobPostings.foreach(j => jobPostings.write(List(j._2.accountId, j._2.accountType.toString, j._2.jobId.toString, j._2.accountAction.toString, j._2.timeStamp.toString)))
      val top2most = MeasureDailySavedJobs.twoDaysThatHadTheMostIncreaseInSavedJobs()
      top2DaysHadTheMostIncreaseInSavedJobs.write(List(top2most._1.toString, top2most._2.toString))
      top2DaysHadTheMostIncreaseInSavedJobs.close
    } finally {
      employerIdsOnly.close
      userSavedJobsAgain.close
      jobPostings.close
    }
  }

}
