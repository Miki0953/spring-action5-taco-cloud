package com.sensible.tacocloud.dao.impl;

import com.sensible.tacocloud.dao.IngredientDao;
import com.sensible.tacocloud.entity.Ingredient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class IngredientDaoImpl implements IngredientDao {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public IngredientDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Iterable<Ingredient> findAll(){
        return jdbcTemplate.query("select id,name,type from ingredient",this::mapRowToIngredient);
    }

    @Override
    public Ingredient findById(String id) {
        return jdbcTemplate.queryForObject("select id,name,type from ingredient where id = ?",this::mapRowToIngredient,id);
    }

    @Override
    public Ingredient save(Ingredient ingredient) {
        jdbcTemplate.update("insert into ingredient(id,name,type) values (?,?,?)",ingredient.getId(),ingredient.getName(),
                ingredient.getType().toString());
        return ingredient;
    }

    /**
     * 将结果集中的每一行数据映射为一个Ingredient对象
     * this:mapRowToIngredient相当于 @link IngredientMapper
     * @param resultSet
     * @param rowNum
     * @return
     * @throws SQLException
     */
    private Ingredient mapRowToIngredient(ResultSet resultSet, int rowNum) throws SQLException{
        return new Ingredient(resultSet.getString("id"),resultSet.getString("name"),
                Ingredient.Type.valueOf(resultSet.getString("type")));
    }
}
