#!/bin/bash
#coding=UTF_8
import random
import time
url_path=[
    "class/112.html",
    "class/128.html",
    "class/145.html",
    "class/146.html",
    "class/131.html",
    "class/130.html",
    "class/231",
    "class/list"
]
ip_slices=[132,156,150,187,156,178,66,88,99,11,22,250]
http_referers=[
    "http://www.baidu.com/s?wd={query}",
    "https://www.sogou.com/web?query={query}",
    "http://cn.bing.com/search?q={query}",
    "https://search.yahoo.com/search?p={query}"
]

serch_keyword=[
    "Spark SQL 实战",
    "Spark Streaming 实战",
    "Spark实战",
    "hadoop实战",
    "hive实战",
    "docker实战",
    "大数据实战"

]

status_codes=["200","404","500"]
def sample_referer():
    if random.uniform(0,1) > 0.7:
        return "_"
    refer_str=random.sample(http_referers,1)
    query_str=random.sample(serch_keyword,1)[0]
    return refer_str[0].format(query=query_str)
def sample_ip():
    slice=random.sample(ip_slices,4)
    return ".".join([str(item) for item in slice])

def sample_status_code():
    return random.sample(status_codes,1)[0]
def sample_url():
    return random.sample(url_path,1)[0]
def generate_log(count=100):
    time_str = time.strftime("%Y-%m-%d %H:%M:%S",time.localtime())
    f=open("/opt/access.log","w+")
    while count >= 1:
        query_log = "{time}\t{url}\t{ip}\t{referer}\t{code}".format(time=time_str,url=sample_url(),ip=sample_ip(),referer=sample_referer(),code=sample_status_code())
        f.write(query_log,"\n")
        print(query_log)
        count=count-1

if __name__ == '__main__':
    generate_log()
    print("sfs")