package com.nowcoder.community.controller;

import com.nowcoder.community.service.AlphaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import sun.text.normalizer.NormalizerBase;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/alpha")
public class AlphaController {
    @Autowired
    private AlphaService alphaService;

    @RequestMapping("/hello")
    @ResponseBody
    public String sayHello() {
        return "Hello Spring Boot.";
    }

    @RequestMapping("/data")
    @ResponseBody
    public String selectdata() {
        return alphaService.find();
    }

    //GET请求
    // /students?current=1&limit=20
    @RequestMapping(path = "/students", method = RequestMethod.GET)
    @ResponseBody
    public String getStudents(
            @RequestParam(name = "current", required = false, defaultValue = "1") int current,
            @RequestParam(name = "limit", required = false, defaultValue = "10") int limit) {
        System.out.println(current);
        System.out.println(limit);
        return "some students";
    }
    // /student/123
    @RequestMapping(path= "/student/{id}",method = RequestMethod.GET)
    @ResponseBody
    public String getStudent(@PathVariable("id") int id)  {
        System.out.println(id);
        return "a student";
    }

    //POST请求
    @RequestMapping(path= "/student", method = RequestMethod.POST)
    @ResponseBody
    public String saveStudent(String name, int age) {
        System.out.println(name);
        System.out.println(age);
        return "success";
    }

    //响应HTML数据
    @RequestMapping(path = "/teacher", method = RequestMethod.GET)
    public ModelAndView getTeacher(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("name", "张三");
        modelAndView.addObject("age", "30");
        modelAndView.setViewName("/demo/view");
        return modelAndView;
    }

    @RequestMapping(path = "/school", method = RequestMethod.GET)
    public String getSchool(Model model) {
        model.addAttribute("name","清华大学");
        model.addAttribute("age","100");
        return "/demo/view";
    }

    //响应JSON(异步数据)
    @RequestMapping(path = "/emp", method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> getEmp() {
        Map<String,Object> map = new HashMap<>();
        map.put("name", "张三");
        map.put("age", "23");
        map.put("salary", "8000.00");
        return map;
    }
}
