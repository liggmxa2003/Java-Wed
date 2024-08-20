package com.lwz.dao;

import com.lwz.entity.LoginAdmin;

public interface LogiAdminDao {
    public LoginAdmin findByUsername(String username);
}
