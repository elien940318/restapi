package com.changkeereum.restapi.controller;

import org.springframework.web.bind.annotation.RestController;
import com.changkeereum.restapi.bean.HelloWorldBean;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
public class HelloWorldController {
    
    @GetMapping("/hello-world")
    public String helloworld() {
        return "hello World";
    }

    /**
     * Bean 타입은 Spring Boot에서 ResponseBody로 변환하여 JSON 포맷으로 전달
     * @return
     */
    @GetMapping("/hello-world-bean")
    public HelloWorldBean HelloWorldBean() {
        return new HelloWorldBean("hello World !");
    }
    
}
