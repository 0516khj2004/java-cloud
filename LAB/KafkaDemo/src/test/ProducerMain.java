package test;

import org.apache.commons.lang3.StringUtils;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;
import java.util.Scanner;

public class ProducerMain {
    private static final String TOPIC_NAME = "test20190425";
    private static final String FIN_MESSAGE = "exit";

    public static void main(String[] args) {
        Properties properties = new Properties();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        KafkaProducer<String, String> producer = new KafkaProducer<>(properties);

        while(true) {
            Scanner sc = new Scanner(System.in);
            System.out.print("Input > ");
            String message = sc.nextLine();

            ProducerRecord<String, String> record = new ProducerRecord<>(TOPIC_NAME, message);
            try {
                producer.send(record, (metadata, exception) -> {
                    if (exception != null) {
                        // some exception
                    }
                });

            } catch (Exception e) {
                // exceptionøO
            } finally {
                producer.flush();
            }

            if(StringUtils.equals(message, FIN_MESSAGE)) {
                producer.close();
                break;
            }
        }
    }}
