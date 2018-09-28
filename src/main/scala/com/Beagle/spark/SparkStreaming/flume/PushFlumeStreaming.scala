package com.Beagle.spark.SparkStreaming.flume

import org.apache.log4j.{Level, Logger}
import org.apache.spark.SparkConf
import org.apache.spark.streaming.flume.FlumeUtils
import org.apache.spark.streaming.{Seconds, StreamingContext}

/**
  * AUTHOR: Wanggc
  * Date : 2018/9/25 18:51
  */
object PushFlumeStreaming {
  def main(args: Array[String]): Unit = {
    Logger.getLogger("org").setLevel(Level.WARN)

    val sparkconf=new SparkConf().setMaster("local[2]").setAppName("PushFlumeStreaming")
    val ssc=new StreamingContext(sparkconf,Seconds(2))

    val flume=FlumeUtils.createStream(ssc,"192.168.100.104",8888)

    flume.map(x=>new String(x.event.getBody.array()).trim).flatMap(_.split(" "))
      .map((_,1)).reduceByKey(_+_).print()

    ssc.start()
    ssc.awaitTermination()
  }
}
