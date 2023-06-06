package com.qucc.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.qucc.mapper.EmpMapper;
import com.qucc.pojo.Emp;
import com.qucc.pojo.PageBean;
import com.qucc.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class EmpServiceImpl implements EmpService {
    @Autowired
    private EmpMapper empMapper;

    /*@Override
    public PageBean page(Integer page, Integer pageSize) {
        Long counts = empMapper.count();
        List<Emp> empList = empMapper.page((page - 1) * pageSize, pageSize);
        return new PageBean(counts, empList);
    }*/

    @Override
    public PageBean page(Integer page, Integer pageSize, String name, Short gender,
                         LocalDate begin,
                         LocalDate end) {
        PageHelper.startPage(page, pageSize);
        List<Emp> empList = empMapper.list(name, gender, begin, end);
        Page<Emp> empPage = (Page<Emp>) empList;
        PageBean pageBean = new PageBean(empPage.getTotal(), empPage.getResult());
        return pageBean;
    }

    /**
     * 批量删除
     * @param ids
     */
    @Override
    public void delete(List<Integer> ids) {
        empMapper.deleteById(ids);
    }

    @Override
    public void add(Emp emp) {
        emp.setCreateTime(LocalDateTime.now());
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.add(emp);
    }

    @Override
    public Emp getById(Integer id) {
        Emp emp = empMapper.getById(id);
        return emp;
    }

    @Override
    public void update(Emp emp) {
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.update(emp);
    }

    @Override
    public Emp login(Emp emp) {
        return empMapper.getByUsernameAndPwd(emp);
    }
}
