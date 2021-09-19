package com.yusael.service;

import com.yusael.dao.EmpDAO;
import com.yusael.entity.Emp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class EmpServiceImpl implements EmpService{
    @Autowired
    private EmpDAO empDAO;
    @Override
    public List<Emp> findAll() {
        return empDAO.findAll();
    }

    @Override
    public void save(Emp emp) {
        emp.setId(UUID.randomUUID().toString());
        empDAO.save(emp);
    }

    @Override
    public void delete(String id) {
        empDAO.delete(id);
    }

    @Override
    public Emp find(String id) {
        return empDAO.find(id);
    }

    @Override
    public void update(Emp emp) {
        empDAO.update(emp);
    }
}
