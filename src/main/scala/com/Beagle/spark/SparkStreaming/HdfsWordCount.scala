package com.Beagle.spark.SparkStreaming

import java.sql.DriverManager

import org.apache.spark.SparkConf
import org.apache.spark.streaming.{Seconds, StreamingContext}

/**
  * AUTHOR: Wanggc
  * Date : 2018/9/15 10:47
  */
object HdfsWordCount {
  def main(args: Array[String]): Unit = {
    val sparkconf=new SparkConf().setMaster("local").setAppName("HdfsWordCount")
    val ssc=new StreamingContext(sparkconf,Seconds(5))
    val lines=ssc.textFileStream("file:///")
    val result=lines.flatMap(_.split(" ")).map((_,1)).reduceByKey(_+_)

    /*
    * 把数据出入数据库
    * */
    result.foreachRDD(rdd=>{
      rdd.foreachPartition(partitionOfRecords=>{
      if(partitionOfRecords.size>0){
        val connection=Connection()
        partitionOfRecords.foreach(record=>{
          val sql="insert into table values ('"+record+"',"++")"
          connection.createStatement().execute(sql)
        })
        connection.close()
      }
      })
    })
//    result.print()
    ssc.start();
    ssc.awaitTermination();
  }
  /*
  * 获取mysql 数据库的连接
  * */
  def Connection()={
    Class.forName("com.mysql.jdbc.Driver")
    DriverManager.getConnection("jdbc:mysql://localhost:3306/spark","root","root")
  }

}
