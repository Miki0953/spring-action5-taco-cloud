package com.sensible.tacocloud.dao;

import com.sensible.tacocloud.entity.Ingredient;
import com.sensible.tacocloud.entity.Taco;

public interface TacoDao {
    Taco save(Taco taco);

    void saveIngredientToTaco(Ingredient ingredient, Long tacoId);
}
