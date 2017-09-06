package com.wls.integrateplugs.hello.controller;

/**
 * Created by wls on 2017/8/24.
 */
import java.util.Locale;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import com.sun.org.apache.regexp.internal.RE;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import springfox.documentation.annotations.ApiIgnore;

@RestController
public class HelloController {

    @RequestMapping(value = "/hello",method = RequestMethod.GET)
    public String hello(Locale locale, Model model) {
        return "hello world";
    }

    @RequestMapping("/helloWorld")
    public String index() {
        return "Hello World";
    }

    /**
     * 使用@RestController时，则使用ModelAndView显示页面
     * @param map
     * @return
     */
    @ApiIgnore
    @RequestMapping(value = "/helloThymeleaf",method = RequestMethod.GET)
    public ModelAndView index(ModelMap map) {
        ModelAndView mv = new ModelAndView("index");
        map.addAttribute("host", "http://blog.didispace.com");
        return mv;
    }

    /**
     * 共享session
     * @param session
     * @return
     */
    @RequestMapping(value = "/uid",method = RequestMethod.GET)
    String uid(HttpSession session) {
        UUID uid = (UUID) session.getAttribute("uid");
        if (uid == null) {
            uid = UUID.randomUUID();
        }
        session.setAttribute("uid", uid);
        return session.getId();
    }

}