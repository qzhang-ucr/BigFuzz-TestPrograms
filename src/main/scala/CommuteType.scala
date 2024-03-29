/**
  * Created by malig on 3/27/18.
  * Modified by Qian on 10/07/2019
  */

import math.log10
import org.apache.spark.{SparkConf, SparkContext}

object CommuteType{

  def main(args: Array[String]) {
    val conf = new SparkConf()
    conf.setMaster("local")
    conf.setAppName("Commute")

    val startTime = System.currentTimeMillis();
    val sc = new SparkContext(conf)

    val data_trip = sc.textFile("/home/qzhang/Programs/BigFuzz-TestPrograms/src/dataset/trips.csv")
    val data_zipcode = sc.textFile("/home/qzhang/Programs/BigFuzz-TestPrograms/src/dataset/zipcode.csv")

    val trips = data_trip.map {
      s =>
        val cols = s.split(",")
        (cols(1), Integer.parseInt(cols(3)) / Integer.parseInt(cols(4)))
    }
    System.out.println("Trips:")
    trips.foreach(println)

    val locations = data_zipcode.map {
      s =>
        val cols = s.split(",")
        (cols(0), cols(1))
    }
      .filter( s => s._2.equals("Palms"))

    System.out.println("Locations:")
    locations.foreach(println)

    val joined = trips.join(locations)
    System.out.println("Joined: " + joined.count())
    joined.foreach(println)

    joined.map { s =>
      // Checking if speed is < 25mi/hr
      if (s._2._1 > 40) {
        ("car", 1)
      } else if (s._2._1 > 15) {
        ("public", 1)
      } else {
        ("walk", 1)
      }
    }.reduceByKey(_ + _)
      .collect
      .foreach(println)
    println("Job Finished")

    println("Time: " + (System.currentTimeMillis() - startTime))
  }
}


/* *
  *
  * map1>","
map3>","
filter2>"",""
map5>"1" , 2, "1"
reduceByKey4> {1,2,3,4}
K_BOUND>2
DAG >reduceByKey4-map5:map5-join:join-map1,filter2:filter2-map3

 */
