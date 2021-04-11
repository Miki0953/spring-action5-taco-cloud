package com.sensible.tacocloud.controller;

import com.sensible.tacocloud.dao.IngredientDao;
import com.sensible.tacocloud.dao.TacoDao;
import com.sensible.tacocloud.entity.Ingredient;
import com.sensible.tacocloud.entity.Ingredient.Type;
import com.sensible.tacocloud.entity.Order;
import com.sensible.tacocloud.entity.Taco;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("order")
public class DesignTacoController {

    private IngredientDao ingredientDao;

    private TacoDao tacoDao;

    public DesignTacoController(IngredientDao ingredientDao, TacoDao tacoDao) {
        this.ingredientDao = ingredientDao;
        this.tacoDao = tacoDao;
    }

    /**
     * 展示taco设计页面
     * @param model Model对象
     * @return
     */
    @GetMapping
    public String showDesignForm(Model model) {
        //硬编码构建Ingredient对象列表
//        List<Ingredient> ingredients = Arrays.asList(
//                new Ingredient("FLTO","Flour Tortilla", Type.WRAP),
//                new Ingredient("COTO","Corn Tortilla", Type.WRAP),
//                new Ingredient("GRBF","Ground Beef", Type.PROTEIN),
//                new Ingredient("CARN","Carnitas", Type.PROTEIN),
//                new Ingredient("TMTO","Diced Tomatoes", Type.VEGGIES),
//                new Ingredient("LETC","Lettuce", Type.VEGGIES),
//                new Ingredient("CHED","Cheddar", Type.CHEESE),
//                new Ingredient("JACK","Monterrey Jack", Type.CHEESE),
//                new Ingredient("SLSA","Salsa", Type.SAUCE),
//                new Ingredient("SRCR","Sour Cream", Type.SAUCE)
//        );
        //改造从数据库获取配料Ingredient信息
        List<Ingredient> ingredients = new ArrayList<>();

        ingredientDao.findAll().forEach(ingredient -> ingredients.add(ingredient));

        Type[] types = Ingredient.Type.values();

        for (Type type : types) {
            model.addAttribute(type.toString().toLowerCase(),filterByType(ingredients,type));
        }
        model.addAttribute("taco",new Taco());
        return "design";
    }

    /**
     * 根据类型查找taco配料
     * @param ingredient
     * @param type
     * @return
     */
    private List<Ingredient> filterByType(List<Ingredient> ingredient,Type type){
        return ingredient.stream().filter(x -> x.getType().equals(type)).collect(Collectors.toList());
    }

    @ModelAttribute(name = "order")
    public Order order(){
        return new Order();
    }

    @ModelAttribute(name = "taco")
    public Taco taco(){
        return new Taco();
    }

    /**
     * 提交设计好的taco表单
     * @param taco
     * @return
     */
    @PostMapping
    public String processDesign(@Valid Taco taco, Errors errors, @ModelAttribute Order order){
        if (errors.hasErrors()){
            return "design";
        }

        //保存taco
        Taco saved = tacoDao.save(taco);

        for (String ingredientId : taco.getIngredients()){
            Ingredient ingredient = ingredientDao.findById(ingredientId);
            tacoDao.saveIngredientToTaco(ingredient,saved.getId());
        }

        order.addDesign(saved);

        log.info("Processing design: " + taco);
        //重定向路径/orders/current(控制器请求路径)
        return "redirect:/orders/current";
    }
}
