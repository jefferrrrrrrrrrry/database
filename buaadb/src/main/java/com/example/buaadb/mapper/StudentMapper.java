package com.example.buaadb.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.buaadb.entity.Student;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface StudentMapper extends BaseMapper<Student> {
    List<Student> login(String name, String password);
    List<Student> find(@Param("sno") int sno, @Param("sname") String sname);
}
