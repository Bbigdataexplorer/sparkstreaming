package com.Beagle.spark.SparkStreaming

import org.apache.log4j.{Level, Logger}
import org.apache.spark.SparkConf
import org.apache.spark.streaming.kafka.KafkaUtils
import org.apache.spark.streaming.{Seconds, StreamingContext}

/**
  * AUTHOR: Wanggc
  * Date : 2018/9/21 10:20
  */
object KafkaStreaming {
  def main(args: Array[String]): Unit = {
    Logger.getLogger("org").setLevel(Level.WARN)
    if(args.length!=4){
      System.out.print("Usage: KafkaStreaming <zkQuorum><groupId><topics><numThreads>")
    }

    val sparkConf=new SparkConf()//.setMaster("local[2]").setAppName("KafkaStreaming")
    val ssc=new StreamingContext(sparkConf,Seconds(2))

    val Array(zkQuorum,groupId,topics,numThreads)=args
    val TOPICS_Map=topics.split(",").map((_,numThreads.toInt)).toMap
//    val lines=KafkaUtils.createStream(ssc,StreamingProperties.ZK_LIST,StreamingProperties.GROUP_ID,StreamingProperties.TOPICS_Map)
    val lines=KafkaUtils.createStream(ssc,zkQuorum,groupId,TOPICS_Map)
    val words=lines.flatMap(_._2.split(" ")).map((_,1)).reduceByKey(_+_)

    words.print()

    ssc.start()
    ssc.awaitTermination()
  }
}
