package com.sensible.tacocloud.entity;


import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Ingredient映射类
 */
public class IngredientMapper implements RowMapper {
    @Override
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
       return new Ingredient(rs.getString("id"),rs.getString("name"),
                Ingredient.Type.valueOf(rs.getString("type")));
    }
}
