import java.io.FileNotFoundException

import scala.io.Source

class CensusAnalyser {
  def loadIndiaCensusData(filePath: String): Int = {
    try {
      if(!filePath.endsWith(".csv")){
        throw new CensusAnalyserException(CustomException.wrongFileType)
      }
      val fileReader = Source.fromFile(filePath)
      var countRow = 0
      for (line <- fileReader.getLines()) {
        val cols = line.split(",").map(_.trim)

        if (cols.length != 4){
          throw new CensusAnalyserException(CustomException.wrongDelimiter)
        }

        if (countRow == 0){
          if(cols(0) != "State" || cols(1) != "Population" || cols(2) != "AreaInSqKm" || cols(3) != "DensityPerSqKm"){
            throw new CensusAnalyserException(CustomException.wrongHeaders)
          }
        }
        countRow += 1
      }
      countRow - 1
    }
    catch {
      case _: FileNotFoundException => throw new CensusAnalyserException(CustomException.wrongFilePath)
    }
  }
  def loadIndiaStateCode(filePath:String):Int={
    try {
      if(!filePath.endsWith(".csv")){
        throw new CensusAnalyserException(CustomException.wrongFileType)
      }
      val fileReader = Source.fromFile(filePath)
      var countRow = 0
      for (line <- fileReader.getLines()) {
        val cols = line.split(",").map(_.trim)

        if (cols.length != 4){
          throw new CensusAnalyserException(CustomException.wrongDelimiter)
        }

        if (countRow == 0){
          if(cols(0) != "SrNo" || cols(1) != "State Name" || cols(2) != "TIN" || cols(3) != "StateCode"){
            throw new CensusAnalyserException(CustomException.wrongHeaders)
          }
        }
        countRow += 1
      }
      countRow - 1
    }
    catch {
      case _: FileNotFoundException => throw new CensusAnalyserException(CustomException.wrongFilePath)
    }
  }
}
object CensusAnalyser{
  def main(args: Array[String]): Unit = {
    val INDIA_CENSUS_CSV_FILE_PATH   = "./src/test/resources/IndiaStateCensusData.csv"
    val censusAnalyzer=new CensusAnalyser()
    val numOfLines=censusAnalyzer.loadIndiaCensusData(INDIA_CENSUS_CSV_FILE_PATH)
    println(numOfLines)
  }
}