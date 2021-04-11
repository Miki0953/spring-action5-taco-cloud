package com.sensible.tacocloud.dao;

import com.sensible.tacocloud.entity.Order;

public interface OrderDao {
    /**
     * 保存订单
     * @param order
     * @return
     */
    Order save(Order order);
}
