package com.qucc.service.impl;

import com.qucc.annotation.Log;
import com.qucc.annotation.QuccLog;
import com.qucc.mapper.DeptMapper;
import com.qucc.mapper.EmpMapper;
import com.qucc.pojo.Dept;
import com.qucc.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DeptServiceImpl implements DeptService {
    @Autowired
    private DeptMapper deptMapper;

    @Autowired
    private EmpMapper empMapper;

    @QuccLog
    @Override
    public List<Dept> list() {
        List<Dept> deptList = deptMapper.deptList();
        return deptList;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delete(Integer id) {
        deptMapper.deleteById(id);
        empMapper.deleteByDeptId(id);
    }

    @Override
    public void add(Dept dept) {
        dept.setCreateTime(LocalDateTime.now());
        dept.setUpdateTime(LocalDateTime.now());

        deptMapper.add(dept);
    }

    @QuccLog
    @Override
    public Dept getById(Integer id) {
        //int i = 1 / 0;
        return deptMapper.getById(id);
    }

    @Override
    public void update(Dept dept) {
        dept.setUpdateTime(LocalDateTime.now());
        deptMapper.update(dept);
    }
}
