package com.example.buaadb.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.buaadb.entity.Class;
import com.example.buaadb.entity.output.ClassInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ClassMapper extends BaseMapper<Class> {
    @Select("SELECT clno, class.scno, Scname FROM (class JOIN school on class.Scno = school.Scno) WHERE Scname LIKE CONCAT('%', #{scname}, '%')")
    List<ClassInfo> find(@Param("scname") String scname);
}
