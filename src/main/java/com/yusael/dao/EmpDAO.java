package com.yusael.dao;

import com.yusael.entity.Emp;
import org.apache.ibatis.annotations.Mapper;


import java.util.List;
@Mapper
public interface EmpDAO {
    List<Emp> findAll(); // 查询所有
    void save(Emp emp);
    void delete(String id);
    Emp find(String id);
    void update(Emp emp);

}
