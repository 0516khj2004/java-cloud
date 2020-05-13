package com.example.msa.springboot.messageq;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.example.msa.springboot.repository.ICoffeeStatusMapper;
import com.example.msa.springboot.repository.dvo.OrderStatusDVO;

@Service
public class KafkaConsumer {

    @Autowired
    ICoffeeStatusMapper iCoffeeStatusMapper;

    @KafkaListener(topics = "example-kafka-test")
    public void processMessage(String kafkaMessage) {

        System.out.println("kafkaMessage : =====> " + kafkaMessage);

        OrderStatusDVO orderStatusDVO = new OrderStatusDVO();
        orderStatusDVO.setOrderHistory(kafkaMessage);

        iCoffeeStatusMapper.insertCoffeeOrderStatus(orderStatusDVO);
    }
}