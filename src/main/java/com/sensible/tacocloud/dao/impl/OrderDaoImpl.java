package com.sensible.tacocloud.dao.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sensible.tacocloud.dao.OrderDao;
import com.sensible.tacocloud.entity.Order;
import com.sensible.tacocloud.entity.Taco;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 订单持久化实现类
 * 使用SimpleJdbcInsert插入数据
 */
@Repository
public class OrderDaoImpl implements OrderDao {

    private SimpleJdbcInsert orderSimpleJdbcInsert;

    private SimpleJdbcInsert orderTacoSimpleJdbcInsert;

    private ObjectMapper objectMapper;

    public OrderDaoImpl(JdbcTemplate jdbcTemplate) {
        this.orderSimpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("taco_order")
                .usingGeneratedKeyColumns("id");
        this.orderTacoSimpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("taco_order_tacos");
        this.objectMapper = new ObjectMapper();
    }

    @Override
    public Order save(Order order) {
        order.setPlacedAt(new Date());
        Long orderId = saveOrderDetail(order);

        List<Taco> tacos = order.getTacos();
        for (Taco taco : tacos) {
            saveTacoToOrder(taco,orderId);
        }
        return order;
    }

    private Long saveOrderDetail(Order order){
        Map<String,Object> values = objectMapper.convertValue(order,Map.class);
        values.put("placedAt",order.getPlacedAt());

        Long orderId = orderSimpleJdbcInsert.executeAndReturnKey(values).longValue();

        return orderId;
    }

    private void saveTacoToOrder(Taco taco, Long orderId){
        Map<String,Object> values = new HashMap<>();
        values.put("tacoOrder",orderId);
        values.put("taco",taco.getId());

        orderTacoSimpleJdbcInsert.execute(values);
    }
}
