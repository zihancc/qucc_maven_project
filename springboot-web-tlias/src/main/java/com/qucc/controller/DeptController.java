package com.qucc.controller;

import com.qucc.annotation.Log;
import com.qucc.pojo.Dept;
import com.qucc.pojo.Result;
import com.qucc.service.DeptService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequestMapping("/depts")
@RestController
public class DeptController {
    //private Logger log = LoggerFactory.getLogger(DeptMapper.class);

    @Autowired
    private DeptService deptService;

    /**
       查询部门数据
     * @return Result结果
     */
    //@RequestMapping(value = "/depts", method = RequestMethod.GET)
    @GetMapping
    public Result deptList(){
        log.info("查询全部部门数据");
        List<Dept> deptList = deptService.list();
        return Result.success(deptList);
    }

    /**
     * 删除部门
     * @param id
     * @return
     */
    @Log
    @DeleteMapping("/{id}")
    public Result deleteDept(@PathVariable Integer id){
        log.info("根据id删除部门:{}", id);
        deptService.delete(id);
        return Result.success();
    }

    /**
     * 添加部门
     * @return
     */
    @Log
    @PostMapping
    public Result add(@RequestBody Dept dept){
        log.info("新增部门： {}", dept);
        deptService.add(dept);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id){
        log.info("根据id查询部门：{}", id);
        Dept dept = deptService.getById(id);
        return Result.success(dept);
    }

    @Log
    @PutMapping
    public Result update(@RequestBody Dept dept){
        log.info("更新部门： {}", dept);
        deptService.update(dept);
        return Result.success();
    }
}
