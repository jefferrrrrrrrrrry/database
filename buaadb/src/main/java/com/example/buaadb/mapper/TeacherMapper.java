package com.example.buaadb.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.buaadb.entity.Teacher;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TeacherMapper extends BaseMapper<Teacher> {
    List<Teacher> login(String name, String password);
    List<Teacher> find(@Param("tno") int tno, @Param("tname") String tname);
}
