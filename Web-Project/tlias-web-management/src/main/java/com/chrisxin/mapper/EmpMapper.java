package com.chrisxin.mapper;

import com.chrisxin.entity.Emp;
import com.chrisxin.entity.EmpQueryParam;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;
import java.util.Objects;

/*员工基本信息*/
@Mapper
public interface EmpMapper {

    /**
     * 查询员工数量
     */
    @Select("select count(*) from emp")
    public Long count();

    /**
     * 分页查询
     */
    /*原始分页查询
    @Select("select e.*,d.name deptName from emp e left join dept d on e.dept_id = d.id order by update_time desc limit #{start},#{pageSize}")
    public List<Emp> list(Integer start, Integer pageSize);
    */

    /*使用PageHelper进行分页查询时，sql语句不需要考虑分页操作和统计操作*/
    //@Select("select e.*,d.name deptName from emp e left join dept d on e.dept_id = d.id order by update_time desc")
    public List<Emp> list(EmpQueryParam empQueryParam);


    /**
     * 添加员工信息
     */
    @Options(useGeneratedKeys = true,keyProperty = "id")//开启功能:获取该sql在数据库生成的主键，并返回给emp的id属性
    @Insert("insert into emp(username, name, gender, phone, job, salary, image, entry_date, dept_id, create_time, update_time) "+
    "values(#{username},#{name},#{gender},#{phone},#{job},#{salary},#{image},#{entryDate},#{deptId},#{createTime},#{updateTime})")
    void add(Emp emp);

    /**
     * 批量删除员工信息
     */
    void deleteByIds(List<Integer> ids);


    /**
     * 根据id查询员工信息
     */
    Emp searchById(Integer id);

    /**
     * 修改员工基本信息
     */
    void updateById(Emp emp);


    /**
     * 员工职位人数统计
     */
    List<Map<String, Object>> getEmpJobData();


    /**
     * 员工性别人数统计
     */
    List<Map<String, Object>> getEmpGenderData();

    /**
     * 查询所有员工信息
     */
    @Select("select * from emp")
    List<Emp> findAll();

    /**
     * 员工登录
     */
    @Select("select * from emp where username=#{username} and password=#{password}")
    Emp loginByUsernameAndPassword(Emp emp);
}
