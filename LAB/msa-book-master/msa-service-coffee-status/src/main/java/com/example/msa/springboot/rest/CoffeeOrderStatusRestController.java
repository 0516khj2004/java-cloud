package com.example.msa.springboot.rest;

import com.example.msa.springboot.repository.ICoffeeStatusMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.msa.springboot.repository.dvo.OrderStatusDVO;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import java.util.Random;

@RestController
public class CoffeeOrderStatusRestController {
    Logger logger = LoggerFactory.getLogger(CoffeeOrderStatusRestController.class);

    @Autowired
    ICoffeeStatusMapper iCoffeeStatusMapper;

    @HystrixCommand
    @RequestMapping(value = "/coffeeOrderStatus", method = RequestMethod.POST)
    public ResponseEntity<OrderStatusDVO> coffeeOrderStatus() {

        OrderStatusDVO orderStatusDVO = iCoffeeStatusMapper.selectCoffeeOrderStatus();

        return new ResponseEntity<OrderStatusDVO>(orderStatusDVO, HttpStatus.OK);
    }

    @RequestMapping(value = "/createStatusTable", method = RequestMethod.PUT)
    public void createStatusTable() {
        iCoffeeStatusMapper.createStatusTable();
    }

    @HystrixCommand
    @RequestMapping(value = "/coffeeOrderStatusWaiting", method = RequestMethod.POST)
    public ResponseEntity<OrderStatusDVO> coffeeOrderStatusWaiting() {
        randomlyRunLong();

        OrderStatusDVO orderStatusDVO = iCoffeeStatusMapper.selectCoffeeOrderStatus();

        return new ResponseEntity<OrderStatusDVO>(orderStatusDVO, HttpStatus.OK);
    }

    private void randomlyRunLong() {
        Random rand = new Random();
        int randomNum = rand.nextInt((3 - 1) + 1) + 1;

        logger.debug("OrderStatusService.coffeeOrderStatusWaiting:" + randomNum);
        if (randomNum == 3)
            sleep();
    }

    private void sleep() {
        try {
            Thread.sleep(11000);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }
}
