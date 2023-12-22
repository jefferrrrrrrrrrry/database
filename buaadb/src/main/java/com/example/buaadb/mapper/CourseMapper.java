package com.example.buaadb.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.buaadb.entity.Course;
import com.example.buaadb.entity.output.CourseInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Mapper
public interface CourseMapper extends BaseMapper<Course> {
    List<CourseInfo> find(@Param("cno") String cno, @Param("cname") String cname, @Param("tname") String tname);
    List<CourseInfo> teacherfind(@Param("tno") String tno);
    Integer approve(@Param("cno") String cno);
    // Double average(@Param("cno") String cno);
    // Double average(@Param("cno") String cno);
    Double average(@Param("cno") String cno);
}
