package com.lwz.dao;

import com.lwz.entity.DormitoryAdmin;

import java.util.List;

public interface DormitoryAdminDao {
    public List<DormitoryAdmin> lint();
    public List<DormitoryAdmin> saearch(String key, String value);
    public Integer zhuce(DormitoryAdmin dormitoryAdmin);
    public Integer save(DormitoryAdmin dormitoryAdmin);
    public Integer update(DormitoryAdmin dormitoryAdmin);
    public Integer delete(Integer id);

}
