package com.example.buaadb.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.buaadb.entity.Teacher;
import com.example.buaadb.entity.output.CourseInfo;
import com.example.buaadb.entity.output.TeacherInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TeacherMapper extends BaseMapper<Teacher> {
    List<TeacherInfo> find(@Param("tno") String tno, @Param("tname") String tname, @Param("scname") String scname);
}
