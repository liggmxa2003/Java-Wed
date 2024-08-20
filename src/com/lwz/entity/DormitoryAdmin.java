package com.lwz.entity;
//数据表实体类
public class DormitoryAdmin {
    private Integer id;
    private String username;
    private String password;
    private String name;
    private String telephone;
    private String gender;
    private String username1;
    private String password1;
    private String name1;





//全参构造


    public DormitoryAdmin(String username, String password, String name, String telephone, String gender, String username1, String password1, String name1) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.telephone = telephone;
        this.gender = gender;
        this.username1 = username1;
        this.password1 = password1;
        this.name1 = name1;
    }

    public DormitoryAdmin(String username1, String password1, String name1) {
        this.username1 = username1;
        this.password1 = password1;
        this.name1 = name1;
    }

    public DormitoryAdmin(Integer id, String username, String password, String name, String telephone, String gender) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.name = name;
        this.telephone = telephone;
        this.gender = gender;
    }

    public DormitoryAdmin(String username, String password, String name, String telephone, String gender) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.telephone = telephone;
        this.gender = gender;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
