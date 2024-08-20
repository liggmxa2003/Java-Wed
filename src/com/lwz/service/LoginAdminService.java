package com.lwz.service;

import com.lwz.dto.LoginAdminDto;

public interface LoginAdminService {
    public LoginAdminDto login(String username, String password);
}
