package kafka;

/**
 * AUTHOR: Wanggc
 * Date : 2018/9/11 16:33
 */

/*
* Kafka 配置文件
* */
public class KafkaProperties {
    public static final String ZK="192.168.100.104:2181,192.168.100.105:2181,192.168.100.106:2181";
    public static final String TOPIC="kafka";
    public static final String BROKER_LIST="192.168.100.104:9092,192.168.100.105:9092,192.168.100.106:9092";
    public static final String GROUP_ID="spark";
}
