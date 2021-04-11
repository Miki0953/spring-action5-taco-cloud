package com.sensible.tacocloud.entity;

import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * 烹饪taco的原料类
 */
@Data
@RequiredArgsConstructor
public class Ingredient {

    private final String id;

    private final String name;

    private final Type type;

    public static enum Type {
        WRAP,PROTEIN,VEGGIES,CHEESE,SAUCE
    }
}
