package com.lwz.service.impl;

import com.lwz.dao.DormitoryAdminDao;
import com.lwz.dao.imp.DormitoryAdminDaoImpl;
import com.lwz.entity.DormitoryAdmin;
import com.lwz.service.DormitoryAdminService;

import java.util.List;

//数据表实现接口类
public class DormitoryAdminServiceImpl implements DormitoryAdminService {

    private DormitoryAdminDao dormitoryAdminDao= new DormitoryAdminDaoImpl();

    @Override
    public List<DormitoryAdmin> list() {
        return this.dormitoryAdminDao.lint();
    }

    @Override
    public List<DormitoryAdmin> search(String key, String value) {
        if (value.equals(""))return this.dormitoryAdminDao.lint();
        return this.dormitoryAdminDao.saearch(key,value);
    }

    @Override
    public void zhuce(DormitoryAdmin dormitoryAdmin) {
        Integer zhuce = this.dormitoryAdminDao.zhuce(dormitoryAdmin);
        if (zhuce != 1) throw new RuntimeException("账户添加失败");
    }

    @Override
    public void save(DormitoryAdmin dormitoryAdmin) {
        Integer save = this.dormitoryAdminDao.save(dormitoryAdmin);
        if (save != 1) throw new RuntimeException("信息添加失败");
    }


    @Override
    public void update(DormitoryAdmin dormitoryAdmin) {
        Integer update = this.dormitoryAdminDao.update(dormitoryAdmin);
        if (update != 1) throw new RuntimeException("信息更新失败");
    }

    @Override
    public void delete(Integer id) {
        Integer delete = this.dormitoryAdminDao.delete(id);
        if (delete != 1) throw new RuntimeException("账户删除失败");
    }
}
