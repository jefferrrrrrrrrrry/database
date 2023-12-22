package com.example.buaadb.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.buaadb.entity.Student;
import com.example.buaadb.entity.Course;
import com.example.buaadb.entity.output.CourseInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface StudentMapper extends BaseMapper<Student> {
    List<Student> findStudent(@Param("sno") String sno, @Param("sname") String sname);
    List<Course> findCourse(@Param("sno") String sno);
    void insert(@Param("cno") String cno, @Param("sno") String sno);
    List<Student> coursechosen(@Param("cno") String cno);
}
