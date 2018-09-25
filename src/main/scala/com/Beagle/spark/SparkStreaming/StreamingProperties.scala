package com.Beagle.spark.SparkStreaming

/**
  * AUTHOR: Wanggc
  * Date : 2018/9/21 10:27
  */
object StreamingProperties {
  val ZK_LIST="192.168.100.104:2181,192.168.100.105:2181,192.168.100.106:2181"
  val GROUP_ID="kafka"
  val TOPICS="KafkaStreaming"
  val TOPICS_Map=TOPICS.split(",").map((_,3)).toMap
}
