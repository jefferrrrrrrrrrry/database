package com.example.buaadb.mapper;

import com.example.buaadb.entity.School;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface SchoolMapper {

    @Select("select ")
    List<School> findAll();
    int insert(School school);
    Integer delete(Integer scid);
}
