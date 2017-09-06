package com.wls.integrateplugs.velocity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@RestController
//@Controller
public class VelocityController {


    @RequestMapping(value = "/helloVelocity",method = RequestMethod.GET)
    public ModelAndView indexVelocity(ModelMap map) {
        ModelAndView mv = new ModelAndView("indexVelocity");
        map.addAttribute("name","王老师");
        map.addAttribute("host", "http://blog.didispace.com");
        return mv;
    }

   /* @RequestMapping("/helloVelocity")
    public String hello(Map<String,Object> map){
        map.put("name", "[Angel -- 守护天使]");
        return "indexVelocity";
    }*/
}
