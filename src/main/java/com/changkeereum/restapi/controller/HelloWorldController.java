package com.changkeereum.restapi.controller;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.changkeereum.restapi.bean.HelloWorldBean;



@RestController
public class HelloWorldController {
    
    @Autowired MessageSource messageSource;
    
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

    /**
     * PathVariable 적용
     * @param name
     * @return
     */
    @GetMapping(path = "/hello-world-bean/{name}")
    public HelloWorldBean helloworldBeanPathVariable(@PathVariable("name") String name) {
        return new HelloWorldBean(String.format("hello World, %s", name));
    }
    
    /**
     * 다국어 처리
     * @param locale
     * @return
     */
    @GetMapping(path = "/hello-world-internationalized")
    public String helloWorldInternationalized(
        @RequestHeader(name="Accept-Language", required = false) Locale locale) 
    {
        return messageSource.getMessage("greeting.message", null, locale);
    }
}
