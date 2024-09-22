package com.changkeereum.restapi.bean;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
// @JsonIgnoreProperties(value={"password", "ssn"})
public class User {

    public User(Integer id, @Size(min = 2, message = "name은 2글자 이상 입력해주시기 바랍니다.") String name,
            @Past(message = "등록일자는 현재시각 이전으로 입력해주시기 바랍니다.") Date joinDate, String password, String ssn) {
        this.id = id;
        this.name = name;
        this.joinDate = joinDate;
        this.password = password;
        this.ssn = ssn;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    @Schema(title = "사용자 ID", description = "사용자 ID는 자동 생성됩니다.")
    private Integer id;
    
    @Schema(title = "사용자 이름", description = "사용자 이름을 입력합니다.")
    @Size(min=2, message="name은 2글자 이상 입력해주시기 바랍니다.")
    private String name;
    
    @Schema(title = "등록일", description = "사용자의 등록일을 입력해 주세요.")
    @Past(message="등록일자는 현재시각 이전으로 입력해주시기 바랍니다.")
    private Date joinDate;

    @Schema(title = "비밀번호", description = "사용자의 비밀번호를 입력해 주세요.")
    private String password;

    @Schema(title = "주민번호", description = "사용자의 주민번호를 입력해 주세요.")
    private String ssn;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<Post> posts;
    
}
