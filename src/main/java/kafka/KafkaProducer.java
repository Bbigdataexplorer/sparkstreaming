package kafka;

import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;

import java.util.Properties;

/**
 * AUTHOR: Wanggc
 * Date : 2018/9/11 16:36
 */
public class KafkaProducer extends Thread{

    private String topic;

    private Producer producer;
    public KafkaProducer(String topic){
        this.topic=topic;

        Properties properties=new Properties();
            properties.put("metadata.broker.list",KafkaProperties.BROKER_LIST);
            properties.put("serializer.class","kafka.serializer.StringEncoder");
            properties.put("request.required.acks","1");
        producer=new Producer<Integer, String>(new ProducerConfig(properties));
    }

    @Override
    public void run() {
        int messageNO=1;
        while(true){
            String message="message_"+messageNO;
            producer.send(new KeyedMessage<Integer,String>(topic,message));
            System.out.println("send:"+message);
            messageNO++;
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
