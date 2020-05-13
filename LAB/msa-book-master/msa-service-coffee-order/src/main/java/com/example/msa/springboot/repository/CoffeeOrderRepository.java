package com.example.msa.springboot.repository;

import com.example.msa.domain.model.OrderEntity;
import com.example.msa.domain.repository.ICoffeeOrderRepository;
import com.example.msa.springboot.repository.jpa.OrderEntityJPO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.msa.springboot.repository.jpa.ICoffeeOrderJpaRepository;

@Repository
public class CoffeeOrderRepository implements ICoffeeOrderRepository {

    @Autowired
    private ICoffeeOrderJpaRepository iCoffeeOrderJpaRepository;

    @Override
    public String coffeeOrderSave(OrderEntity orderEntity) {

        OrderEntityJPO orderEntityJPO = new OrderEntityJPO();
        orderEntityJPO.setOrderNumber(orderEntity.getOrderNumber());
        orderEntityJPO.setCoffeeName(orderEntity.getCoffeeName());
        orderEntityJPO.setCoffeeCount(orderEntity.getCoffeeCount());
        orderEntityJPO.setCustomerName(orderEntity.getCustomerName());

        iCoffeeOrderJpaRepository.save(orderEntityJPO);

        return orderEntityJPO.getId();
    }
}
