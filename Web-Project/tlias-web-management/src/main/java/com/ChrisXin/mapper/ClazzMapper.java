package com.ChrisXin.mapper;

import com.ChrisXin.entity.Clazz;
import com.ChrisXin.entity.ClazzQueryParam;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ClazzMapper {

    /**
     * 根据参数查询所有班级信息
     */
    List<Clazz> page(ClazzQueryParam clazzQueryParam);

    /**
     * 根据id删除班级信息
     */
    @Delete("delete from clazz where id=#{id}")
    void deleteById(Integer id);

    /**
     * 添加班级信息
     */
    void add(Clazz clazz);

    /**
     * 根据id查询班级信息
     */
    @Select("select * from clazz where id = #{id}")
    Clazz searchById(Integer id);

    /**
     * 修改班级信息
     */
    void update(Clazz clazz);

    /**
     * 查询所有班级信息
     */
    List<Clazz> list();

    /**
     * 判断班级是否为空
     */
    @Select("select count(*) from student where clazz_id = #{id}")
    Long isEmpty(Integer id);
}
