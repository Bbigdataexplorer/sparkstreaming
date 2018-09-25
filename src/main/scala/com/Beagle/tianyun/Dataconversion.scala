package com.Beagle.tianyun

import java.io._

import scala.io.Source

/**
  * AUTHOR: Wanggc
  * Date : 2018/9/19 9:12
  */

object Dataconversion {
  def show(): Unit ={
    val file_a = new File("G:/天云/天云大数据任务/任务1/积分系统计算相关/data/测试数据.txt")
    val file_b = new File("G:/天云/天云大数据任务/任务1/积分系统计算相关/测试数据1.txt")
    val filereader = new InputStreamReader(new FileInputStream(file_a), "GBK")
    val filewriter = new OutputStreamWriter(new FileOutputStream(file_b), "UTF-8")
    val br = new BufferedReader(filereader)
    val bw = new BufferedWriter(filewriter)
    while (br.readLine() != null) {
      var tianyundata=br.readLine()
      val	len=tianyundata.length()
      var date=tianyundata.substring(0,12)
      var bankname1=tianyundata.substring(12,len-10).trim()
      var number=tianyundata.substring(len-10,len)
      var a=date+"|"+bankname1+"|"+number
      System.out.println(a)
      bw.write(tianyundata)
    }
    br.close()
    bw.close()
  }
  def main(args: Array[String]): Unit = {
    val sourcefile=Source.fromFile("G:/天云/天云大数据任务/任务1/积分系统计算相关/data/测试数据.txt","gbk")
    val lines=sourcefile.getLines().toArray
    lines

  }

}
