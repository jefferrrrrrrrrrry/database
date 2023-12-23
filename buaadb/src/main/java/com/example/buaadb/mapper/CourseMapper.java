package com.example.buaadb.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.buaadb.entity.Course;
import com.example.buaadb.entity.output.CourseInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CourseMapper extends BaseMapper<Course> {
    List<CourseInfo> find(@Param("cno") String cno, @Param("cname") String cname, @Param("tname") String tname);
    List<CourseInfo> studentselect(@Param("sno") String sno, @Param("cno") String cno, @Param("cname") String cname, @Param("tname") String tname);
    List<CourseInfo> findApprove(@Param("tno") String tno, @Param("cno") String cno, @Param("cname") String cname, @Param("tname") String tname);
    List<CourseInfo> findPend(@Param("tno") String tno, @Param("cno") String cno, @Param("cname") String cname, @Param("tname") String tname);
    Integer approve(@Param("cno") String cno);
    List<CourseInfo> average();
    List<Object> managerfindPend(@Param("cno") String cno, @Param("cname") String cname, @Param("tname") String tname);
}
