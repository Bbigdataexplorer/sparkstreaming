package kafka;

/**
 * AUTHOR: Wanggc
 * Date : 2018/9/12 8:25
 */
public class KafkaClientApp {
    public static void main(String[] args) {
        new KafkaProducer(KafkaProperties.TOPIC).start();
        new KafkaConsumer(KafkaProperties.TOPIC).start();
    }
}
