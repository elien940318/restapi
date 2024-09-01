package com.changkeereum.restapi.bean;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Id;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(value={"password", "ssn"})
public class User {

    @Id
    private Integer id;
    
    @Size(min=2, message="name은 2글자 이상 입력해주시기 바랍니다.")
    private String name;
    
    @Past(message="등록일자는 현재시각 이전으로 입력해주시기 바랍니다.")
    private Date joinDate;

    //@JsonIgnore
    private String password;
    //@JsonIgnore
    private String ssn;
}
