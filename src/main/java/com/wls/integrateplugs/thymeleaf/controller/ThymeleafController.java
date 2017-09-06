package com.wls.integrateplugs.thymeleaf.controller;

/**
 * Created by wls on 2017/8/24.
 */
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import springfox.documentation.annotations.ApiIgnore;

@RestController
public class ThymeleafController {

    @GetMapping("/hi")
    public String hello(Locale locale, Model model) {
        model.addAttribute("greeting", "Hello!");

        Date date = new Date();
        DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
        String formattedDate = dateFormat.format(date);
        model.addAttribute("currentTime", formattedDate);

        return "hello";
    }

    /**
     * 使用@RestController时，则使用ModelAndView显示页面
     * @param map
     * @return
     */
    @ApiIgnore
    @RequestMapping(value = "/helloThymeleaf",method = RequestMethod.GET)
    public ModelAndView indexThymeleaf(ModelMap map) {
        ModelAndView mv = new ModelAndView("indexThymeleaf");
        map.addAttribute("name","王老师");
        map.addAttribute("host", "http://blog.didispace.com");
        return mv;
    }

}
