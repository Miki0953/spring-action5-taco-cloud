package com.sensible.tacocloud.dao.impl;

import com.sensible.tacocloud.dao.TacoDao;
import com.sensible.tacocloud.entity.Ingredient;
import com.sensible.tacocloud.entity.Taco;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.sql.Types;
import java.util.Arrays;
import java.util.Date;

@Repository
public class TacoDaoImpl implements TacoDao {

    private JdbcTemplate jdbcTemplate;

    public TacoDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Taco save(Taco taco) {
        Long tacoId = saveTacoInfo(taco);
        taco.setId(tacoId);

        return taco;
    }

    /**
     * 保存玉米卷taco信息，并返回保存的taco的Id
     * @param taco
     * @return
     */
    private Long saveTacoInfo(Taco taco) {
        taco.setCreateAt(new Date());
        //创建一个PreparedStatementCreator工厂
        PreparedStatementCreatorFactory preparedStatementCreatorFactory = new PreparedStatementCreatorFactory(
                "insert into taco(name,createdAt) values(?,?)", Types.VARCHAR,Types.TIMESTAMP);

        //设置PreparedStatement是否返回自动生成的主键
        preparedStatementCreatorFactory.setReturnGeneratedKeys(true);

        //获取PreparedStatementCreator
        PreparedStatementCreator preparedStatementCreator = preparedStatementCreatorFactory
                .newPreparedStatementCreator(
                    Arrays.asList(
                        taco.getName(),
                        new Timestamp(taco.getCreateAt().getTime())
                    )
                );

        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(preparedStatementCreator,keyHolder);


        return keyHolder.getKey().longValue();
    }

    /**
     * 保存taco_ingredient
     * @param ingredient
     * @param tacoId
     */
    @Override
    public void saveIngredientToTaco(Ingredient ingredient, Long tacoId){
        jdbcTemplate.update(
                "insert into taco_ingredients(taco,ingredient) values (?,?)",tacoId,ingredient.getId()
        );
    }
}
