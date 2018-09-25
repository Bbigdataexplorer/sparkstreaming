package com.Beagle.spark.SparkStreaming

import org.apache.spark.SparkConf
import org.apache.spark.streaming.{Seconds, StreamingContext}

/**
  * AUTHOR: Wanggc
  * Date : 2018/9/21 9:28
  */
object socketTextStreaming {
  def main(args: Array[String]): Unit = {
    val sparkconf=new SparkConf().setMaster("local[2]").setAppName("socketTextStreaming")
    val ssc=new StreamingContext(sparkconf,Seconds(2))

    val lines=ssc.socketTextStream("192.168.100.104",9999)
    val words=lines.flatMap(_.split(" ")).map((_,1)).reduceByKey(_+_)
    words.print()
    ssc.start()
    ssc.awaitTermination()
  }

}
