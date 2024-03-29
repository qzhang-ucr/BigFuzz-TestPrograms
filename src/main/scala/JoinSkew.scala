import org.apache.spark.sql.SparkSession
import org.apache.spark.{SparkConf, SparkContext}

object JoinSkew {


  def main(args: Array[String]): Unit = {

    val conf = new SparkConf()
//    conf.setMaster("local[6]")
    conf.setAppName("Join")

    val sc = new SparkContext(conf)


//    val spark = SparkSession.builder().appName("SimpleJoin").getOrCreate()

    var list_t1 = List(("index","1.2.3.4"),("about","3.4.5.6"),("index","1,2,3,4"),("index","1.3.3.1"))

    for (_ <-1 to 20)
    {
      list_t1=list_t1 ::: list_t1
    }
    list_t1=("hello","2.3.1.2")+:list_t1

    val visit = sc.parallelize(list_t1,2)

    var list_t2=List(("hello","home"),("hi","home"))
    for (_<-1 to 15)
    {
      list_t2=list_t2 ::: list_t2
    }
    val page = sc.parallelize(list_t2,2)

    visit.join(page).foreach(println)
    page.join(visit).foreach(println)
  }


}