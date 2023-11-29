package com.example.buaadb.mapper;

import com.example.buaadb.entity.Course;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CourseMapper {
    List<Course> findAll();

}
