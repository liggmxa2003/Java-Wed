package com.lwz.controller;

import com.lwz.entity.DormitoryAdmin;
import com.lwz.service.impl.DormitoryAdminServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/dormitoryAdmin")
public class DormitoryAdminService extends HttpServlet {

    private com.lwz.service.DormitoryAdminService dormitoryAdminService = new DormitoryAdminServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        String method = req.getParameter("method");
        switch (method){
            case "list":
            req.setAttribute("list",this.dormitoryAdminService.list());
            req.getRequestDispatcher("shu_ju.jsp").forward(req,resp);
            break;
            case "search"://查询下属管理表单
                String key = req.getParameter("key");
                String value = req.getParameter("value");
                req.setAttribute("list",this.dormitoryAdminService.search(key,value));
                req.getRequestDispatcher("shu_ju.jsp").forward(req,resp);
                break;
            case "zhuce"://注册账户
                String username1 = req.getParameter("username1");
                String password1 = req.getParameter("password1");
                String name1 = req.getParameter("name1");
                this.dormitoryAdminService.zhuce(new DormitoryAdmin(null,username1,password1,name1,null,null));
                req.setAttribute("zhuce",this.dormitoryAdminService.list());
                req.setAttribute("chenggong","账户注册成功已返回登录页面");
                req.getRequestDispatcher("Login.jsp").forward(req,resp);
                break;
            case "save"://添加下属
                String username = req.getParameter("username");
                String password = req.getParameter("password");
                String name = req.getParameter("name");
                String gender = req.getParameter("gender");
                String telephone = req.getParameter("telephone");
                this.dormitoryAdminService.save(new DormitoryAdmin(username,password,name,gender,telephone));
                resp.sendRedirect("dormitoryAdmin?method=list");
                break;
            case "update"://修改
                String idStr = req.getParameter("id");
                Integer id = Integer.parseInt(idStr);
                 username = req.getParameter("username");
                 password = req.getParameter("password");
                 name = req.getParameter("name");
                 gender = req.getParameter("gender");
                 telephone = req.getParameter("telephone");
                 this.dormitoryAdminService.update(new DormitoryAdmin(id,username,password,name,gender,telephone));
                 resp.sendRedirect("dormitoryAdmin?method=list");
                break;
            case "delete"://删除
                idStr = req.getParameter("id");
                id = Integer.parseInt(idStr);
                this.dormitoryAdminService.delete(id);
                resp.sendRedirect("dormitoryAdmin?method=list");
                break;

        }
    }
}
