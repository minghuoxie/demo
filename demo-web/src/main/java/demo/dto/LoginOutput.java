package demo.dto;

import io.swagger.annotations.ApiModelProperty;

public class LoginOutput {
    @ApiModelProperty(value = "返回username")
    private String username;

    @ApiModelProperty(value = "password")
    private String password;


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
