package com.ChrisXin.mapper;

import com.ChrisXin.entity.EmpExpr;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/*员工工作经历*/
@Mapper
public interface EmpExprMapper {

    void addBatch(List<EmpExpr> exprList);

    /**
     * 批量删除员工工作经历信息
     */
    void deleteByEmpId(List<Integer> empIds);


    /**
     * 查询员工工作经历信息
     */
    List<EmpExpr> searchByEmpId(Integer empId);
}
