package com.heleeos.cms.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping(value = "from")
public class FromController {

    @RequestMapping(value = "add.html")
    public ModelAndView toAdd(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView("from/add");
        /*int id = NumberUtils.toInt(request.getParameter("id"), 0);
        if(id != 0) {
            modelAndView.addObject("bean", blogService.get(id));
        }
        modelAndView.addObject("types", blogTypeService.gets());*/
        return modelAndView;
    }
}
