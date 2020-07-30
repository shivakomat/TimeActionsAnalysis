import reportgenerator.ReportsGenerator

object AppMain extends App {

  val parser = new scopt.OptionParser[Config]("Timed Actions App") {
    head("---------------Glass Door Timed Actions Analysis Assignment-----------------------------")
    opt[String]('i', "dataInput") required() action { (x, c) =>
      c.copy(inputDataSrc = x) } text("timed actions data set input path")
    opt[String]('o', "outputDir") required() action { (x, c) =>
      c.copy(outputDir = x) } text("invalid output path")
    }

   parser.parse(args, Config()) map { config =>
      new ReportsGenerator(config.inputDataSrc, config.outputDir)
        .run()
   }

}
