package com.ssafy.youandi.dto.request;

import com.ssafy.youandi.entity.Role;
import com.ssafy.youandi.entity.user.User;
import io.swagger.annotations.ApiModelProperty;
import lombok.ToString;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Valid
@ToString
public class JoinRequestDto {
    @ApiModelProperty(required = true)
    @NotNull
    @NotBlank(message = "이메일을 입력해주세요")
    @Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+.[A-Za-z]{2,6}$", message = "이메일 형식에 맞지 않습니다.")
    private String email;
    @ApiModelProperty(required = true)
    @NotNull
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d$@$!%*#?&]{8,}$")
    @NotBlank(message = "비밀번호를 입력해주세요")
    private String password;
    private String checkedpassword;
    @ApiModelProperty(required = true)
    @NotNull
    @NotBlank(message = "닉네임을 입력해주세요")
    private String nickname;


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCheckedpassword() {
        return checkedpassword;
    }

    public void setCheckedpassword(String checkedpassword) {
        this.checkedpassword = checkedpassword;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

}
