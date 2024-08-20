package com.lwz.dto;

import com.lwz.entity.LoginAdmin;

public class LoginAdminDto {
    private Integer code;
    private LoginAdmin systemAdmin;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public LoginAdmin getSystemAdmin() {
        return systemAdmin;
    }

    public void setSystemAdmin(LoginAdmin systemAdmin) {
        this.systemAdmin = systemAdmin;
    }
}
