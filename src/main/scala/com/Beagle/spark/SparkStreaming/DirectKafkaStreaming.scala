package com.Beagle.spark.SparkStreaming

import kafka.serializer.StringDecoder
import org.apache.log4j.{Level, Logger}
import org.apache.spark.streaming.kafka.KafkaUtils
import org.apache.spark.streaming.{Seconds, StreamingContext}
import org.apache.spark.{SparkConf, SparkContext}

/**
  * AUTHOR: Wanggc
  * Date : 2018/9/25 15:52
  */
object DirectKafkaStreaming {
  def main(args: Array[String]): Unit = {
    Logger.getLogger("org").setLevel(Level.WARN)

    val sparkconf=new SparkConf().setAppName("DirectKafkaStreaming").setMaster("local[2]")
    val ssc=new StreamingContext(sparkconf,Seconds(2))

    val Array(brokers,topics)=args
    val kafkaParams=Map[String,String]("metadata.broker.list"-> brokers)
    val topicSet=topics.split(",").toSet

    val lines=KafkaUtils.createDirectStream[String,String,StringDecoder,StringDecoder](ssc,kafkaParams,topicSet)
    val words=lines.flatMap(_._2.split(" ")).map((_,1)).reduceByKey(_+_)
    words.print()
    ssc.start()
    ssc.awaitTermination()


  }
}

