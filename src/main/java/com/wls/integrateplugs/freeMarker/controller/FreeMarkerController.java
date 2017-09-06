package com.wls.integrateplugs.freeMarker.controller;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class FreeMarkerController {

    @RequestMapping(value = "/helloFreeMarker",method = RequestMethod.GET)
    public ModelAndView indexFreeMarker(ModelMap map) {
        ModelAndView mv = new ModelAndView("indexFreeMarker");
        map.addAttribute("name","王老师");
        map.addAttribute("host", "http://blog.didispace.com");
        return mv;
    }
}
