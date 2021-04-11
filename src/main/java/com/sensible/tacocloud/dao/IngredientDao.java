package com.sensible.tacocloud.dao;

import com.sensible.tacocloud.entity.Ingredient;

public interface IngredientDao {
    /**
     * 查询所有配料信息
     * @return
     */
    Iterable<Ingredient> findAll();

    /**
     * 根据Ingredient的id查询Ingredient
     * @param id
     * @return
     */
    Ingredient findById(String id);

    /**
     * 保存墨西哥玉米卷配料信息
     * @param ingredient
     * @return
     */
    Ingredient save(Ingredient ingredient);
}
