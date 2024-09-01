package com.changkeereum.restapi.controller;

import org.springframework.web.bind.annotation.RestController;
import com.changkeereum.restapi.bean.HelloWorldBean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;



@RestController
public class HelloWorldController {
    
    @GetMapping(path = "/hello-world")
    public String helloworld() {
        return "hello World";
    }

    /**
     * \n@ResponseBody에서 Bean 타입은 Spring Boot에서 ResponseBody로 변환하여 JSON 포맷으로 전달
     * @return
     */
    @GetMapping(path = "/hello-world-bean")
    public HelloWorldBean HelloWorldBean() {
        return new HelloWorldBean("hello World !");
    }

    @GetMapping(path = "/hello-world-bean/{name}")
    public HelloWorldBean helloworldBeanPathVariable(@PathVariable("name") String name) {
        return new HelloWorldBean(String.format("hello World, %s", name));
    }
    
    
}
