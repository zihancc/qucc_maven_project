package com.qucc.service;

import com.qucc.pojo.Dept;

import java.util.List;

public interface DeptService {
    /**
        查询全部部门数据的
     * @return
     */
    List<Dept> list();

    /**
       删除部门
     * @param id
     */
    void delete(Integer id);

    /**
       新增部门
     * @param dept
     */
    void add(Dept dept);

    Dept getById(Integer id);

    void update(Dept dept);
}
