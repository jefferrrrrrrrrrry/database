package com.example.buaadb.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.buaadb.entity.Teacher;
import com.example.buaadb.entity.output.CourseInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TeacherMapper extends BaseMapper<Teacher> {
    List<Teacher> login(String name, String password);
    List<Teacher> find(@Param("tno") String tno, @Param("tname") String tname);
    List<CourseInfo> findApprove(@Param("tno") String tno);
    List<CourseInfo> findPend(@Param("tno") String tno);
}
