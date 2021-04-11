package com.sensible.tacocloud.entity;

import lombok.Data;
import org.hibernate.validator.constraints.CreditCardNumber;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class Order {
    private Long id;

    @NotBlank(message = "必须要有名称")
    private String name;

    @NotBlank(message = "必须要有街道")
    private String street;

    @NotBlank(message = "必须要有区县")
    private String city;

    @NotBlank(message = "必须要有市区")
    private String state;

    @NotBlank(message = "必须要有游遍")
    private String zip;

    @CreditCardNumber(message = "无效的信用卡号")
    private String ccNumber;


    @Pattern(regexp = "(^0[1-9]|1[0-2])([\\/])([1-9][0-9])$",message = "格式必须为 MM/YY")
    private String ccExpiration;

    @Digits(integer = 3,fraction = 0,message = "无效的CVV")
    private String ccCVV;

    private Date placedAt;

    private List<Taco> tacos = new ArrayList<>();

    public void addDesign(Taco taco) {
        this.tacos.add(taco);
    }
}
