package com.example.msa.domain.repository;

import com.example.msa.domain.model.OrderEntity;

public interface ICoffeeOrderRepository {

    public String coffeeOrderSave(OrderEntity orderEntity);

}
