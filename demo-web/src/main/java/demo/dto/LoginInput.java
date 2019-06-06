package demo.dto;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;

public class LoginInput {
    @ApiModelProperty(value = "用户名", required = true)
    @NotNull(message = "用户名必填")
    private String userName;

    @ApiModelProperty(value = "密码", required = true)
    @NotNull(message = "密码必填")
    private String password;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
