package com.changkeereum.restapi.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.changkeereum.restapi.bean.AdminUser;
import com.changkeereum.restapi.bean.AdminUserV2;
import com.changkeereum.restapi.bean.User;
import com.changkeereum.restapi.dao.UserDaoService;
import com.changkeereum.restapi.exception.UserNotFoundException;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;



@RestController
@RequestMapping("/admin")
public class AdminUserController {

    @Autowired UserDaoService service;

    /**
     * return문으로 MappingJacksonValue로 고정하여 사용할 수 밖에 없는 방식.
     * 실 업무에서는 DB에서 값을 가져올 때 필요한 값만 지정하여 가져오도록 한 후,
     * vo annotation에 @JsonInclude(JsonInclude.Include.NON_EMPTY)를 적용하여 제외하도록 할 것.
     * @param id
     * @return
     */
    // @GetMapping("/v1/users/{id}")
    // @GetMapping(value="/users/{id}", params="version=1")
    // @GetMapping(value="/users/{id}", headers="X-API-VERSION=1")
    @GetMapping(value="/users/{id}", produces="application/vnd.company.appv1+json")
    public MappingJacksonValue retrieveUserForAdmin(@PathVariable("id") int id) {
        User user = service.findOne(id);

        AdminUser adminUser = new AdminUser();

        if(ObjectUtils.isEmpty(user)) {
            throw new UserNotFoundException(String.format("ID[%s] not found", id));
        }
        BeanUtils.copyProperties(user, adminUser);
        
        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("id", "name", "joinDate", "ssn");
        SimpleFilterProvider filters = new SimpleFilterProvider().addFilter("UserInfo", filter);
        
        MappingJacksonValue mapping = new MappingJacksonValue(adminUser);
        mapping.setFilters(filters);

        return mapping;
    }

    // @GetMapping("/v2/users/{id}") // URL Versioning; Twitter
    // @GetMapping(value="/users/{id}", params="version=2") // Request Parameter Versioning; Amazon
    // @GetMapping(value="/users/{id}", headers="X-API-VERSION=2") // (Custom) Headers Versioning; Microsoft
    @GetMapping(value="/users/{id}", produces="application/vnd.company.appv2+json") // MIME Type Versioning; GitHub
    public MappingJacksonValue retrieveUserForAdminV2(@PathVariable("id") int id) {
        User user = service.findOne(id);

        AdminUserV2 adminUser = new AdminUserV2();

        if(ObjectUtils.isEmpty(user)) {
            throw new UserNotFoundException(String.format("ID[%s] not found", id));
        }
        BeanUtils.copyProperties(user, adminUser);
        adminUser.setGrade("VIP");
        
        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("id", "name", "joinDate", "grade");
        SimpleFilterProvider filters = new SimpleFilterProvider().addFilter("UserInfoV2", filter);
        
        MappingJacksonValue mapping = new MappingJacksonValue(adminUser);
        mapping.setFilters(filters);

        return mapping;
    }
    
    @GetMapping("/users")
    public MappingJacksonValue retrieveAllUsersForAdmin() {
        List<User> users = service.findAll();

        List<AdminUser> adminUsers = new ArrayList<>();
        AdminUser adminUser = null;
        for(User user : users) {
            adminUser = new AdminUser();
            BeanUtils.copyProperties(user, adminUser);
            adminUsers.add(adminUser);
        }
        
        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("id", "name", "joinDate", "ssn");
        SimpleFilterProvider filters = new SimpleFilterProvider().addFilter("UserInfo", filter);
        
        MappingJacksonValue mapping = new MappingJacksonValue(adminUsers);
        mapping.setFilters(filters);

        return mapping;
    }

}
