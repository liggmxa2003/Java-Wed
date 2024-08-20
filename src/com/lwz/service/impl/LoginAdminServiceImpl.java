package com.lwz.service.impl;

import com.lwz.dao.LogiAdminDao;
import com.lwz.dao.imp.LoginAdminDaoImpl;
import com.lwz.dto.LoginAdminDto;
import com.lwz.entity.LoginAdmin;
import com.lwz.service.LoginAdminService;

public class LoginAdminServiceImpl implements LoginAdminService {

    private LogiAdminDao logiAdminDao = new LoginAdminDaoImpl();

    @Override
    public LoginAdminDto login(String username, String password) {
        //1、通过username查询数据库
        //2、查询结果为空，username错误
        //3、查询结果不为空，再判断password是否正确
        LoginAdmin systemAdmin = this.logiAdminDao.findByUsername(username);
        LoginAdminDto loginAdminDto = new LoginAdminDto();
        if(systemAdmin == null){
            loginAdminDto.setCode(-1);//账户不存在
        } else {
            if(!systemAdmin.getPassword().equals(password)){
                loginAdminDto.setCode(-2);//密码错误
            } else {
                loginAdminDto.setCode(0);//登录成功
                loginAdminDto.setSystemAdmin(systemAdmin);
            }
        }
        return loginAdminDto;
    }
}
