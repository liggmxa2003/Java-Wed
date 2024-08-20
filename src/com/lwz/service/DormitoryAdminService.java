package com.lwz.service;
//数据表接口
import com.lwz.entity.DormitoryAdmin;

import java.util.List;

public interface DormitoryAdminService {
    public List<DormitoryAdmin> list();
    public List<DormitoryAdmin> search(String key, String value);
    public void zhuce(DormitoryAdmin dormitoryAdmin);
    public void save(DormitoryAdmin dormitoryAdmin);
    public void update(DormitoryAdmin dormitoryAdmin);
    public void delete(Integer id);
}
