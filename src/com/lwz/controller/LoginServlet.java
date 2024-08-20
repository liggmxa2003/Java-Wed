package com.lwz.controller;
//控制器

import com.lwz.dto.LoginAdminDto;
import com.lwz.service.LoginAdminService;
import com.lwz.service.impl.LoginAdminServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/account")
public class LoginServlet extends HttpServlet {

    private LoginAdminService loginAdminService = new LoginAdminServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String method = req.getParameter("method");
        switch (method){
            case "login"://判断method=longin执行下面的登录参数
                String username = req.getParameter("username");
                String password = req.getParameter("password");
                System.out.println("拿到账号："+username);
                System.out.println("拿到账号："+password);
                LoginAdminDto loginAdminDto = this.loginAdminService.login(username,password);
                switch (loginAdminDto.getCode()){
                    case -1:
                        req.setAttribute("usernameError","账号不存在！");
                        req.getRequestDispatcher("Login.jsp").forward(req,resp);
                        break;
                    case -2:
                        req.setAttribute("passwordError","密码错误！");
                        req.getRequestDispatcher("Login.jsp").forward(req,resp);
                        break;
                    case 0:
                        //登录成功跳转
                        HttpSession session = req.getSession();
                        session.setAttribute("systemAdmin", loginAdminDto.getSystemAdmin());
                        resp.sendRedirect("zhu_ye.jsp");
                        break;
                }
            break;
            case "logout":
                HttpSession session = req.getSession();
                session.removeAttribute("systemAdmin");
                resp.sendRedirect("login/Login.jsp");
                break;
        }

    }
}
