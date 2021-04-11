package com.sensible.tacocloud.controller;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

/**
 * 测试HomeController类
 * WebMvcTest表明针对HomeController的测试
 */
@RunWith(SpringRunner.class)
@WebMvcTest(HomeController.class)
public class HomeControllerTest {

    //注入MockMvc
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void TestHomePage() throws Exception{
        /**
         * 1.模拟发送一个对根路径"/"的GET请求，是POST请求就用post方法
         * 2.期望得到一个Http 200的响应
         * 3.期待得到一个名为home的视图
         * 4.期待渲染后的视图的内容应该包含文本"Welcome to..."
         */
        mockMvc.perform(get("/")).andExpect(status().isOk()).andExpect(view().name("home"))
                .andExpect(content().string(
                        containsString("Welcome to...")
                )).andDo(MockMvcResultHandlers.print());
    }
}
