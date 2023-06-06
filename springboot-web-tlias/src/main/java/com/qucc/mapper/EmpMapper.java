package com.qucc.mapper;

import com.qucc.pojo.Emp;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDate;
import java.util.List;

@SuppressWarnings("MybatisXMapperMethodInspection")
@Mapper
public interface EmpMapper {

    /**
       查询总记录数
     * @return
     */
    /*@Select("select COUNT(*) from emp")
    public Long count();*/

    /**
       分页查询获取列表数据的方法
     * @param
     * @param
     * @return 员工列表
     */
    /*@Select("select * from emp limit #{begin}, #{pageSize}")
    public List<Emp> page(Integer begin, Integer pageSize);*/

    //@Select("select * from emp")
    public List<Emp> list(String name, Short gender,
                          LocalDate begin,
                          LocalDate end);

    public void deleteById(List<Integer> ids);

    public void add(Emp emp);

    @Select("select * from emp where id = #{id}")
    Emp getById(Integer id);

    void update(Emp emp);

    @Select("select * from emp where username = #{username} and password = #{password}")
    Emp getByUsernameAndPwd(Emp emp);

    @Delete("delete from emp where dept_id = #{deptId}")
    void deleteByDeptId(Integer deptId);
}
