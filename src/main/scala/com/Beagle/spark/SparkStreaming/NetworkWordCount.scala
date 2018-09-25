package com.Beagle.spark.SparkStreaming

import org.apache.spark.SparkConf
import org.apache.spark.streaming.{Seconds, StreamingContext}

/**
  * AUTHOR: Wanggc
  * Date : 2018/9/14 17:45
  */
object NetworkWordCount {
  def main(args: Array[String]): Unit = {
    val sparkConf=new SparkConf().setMaster("local[2]").setAppName("NetworkWordCount")
//    val s=new SparkContext(sparkConf);
    val ssc= new StreamingContext(sparkConf,Seconds(5))
    val lines=ssc.socketTextStream("localhost",8888)
    val result=lines.flatMap(_.split(" ")).map((_,1)).reduceByKey(_+_)
    result.print()
    ssc.start();
    ssc.awaitTermination();
  }

}
