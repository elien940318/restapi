package com.changkeereum.restapi.bean;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFilter;

import jakarta.persistence.Id;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonFilter("UserInfo")
public class AdminUser {

    @Id
    private Integer id;
    
    @Size(min=2, message="name은 2글자 이상 입력해주시기 바랍니다.")
    private String name;
    
    @Past(message="등록일자는 현재시각 이전으로 입력해주시기 바랍니다.")
    private Date joinDate;

    private String password;
    private String ssn;
}
