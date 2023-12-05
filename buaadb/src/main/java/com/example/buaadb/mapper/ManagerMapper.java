package com.example.buaadb.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.buaadb.entity.Manager;
import com.example.buaadb.entity.Teacher;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ManagerMapper extends BaseMapper<Manager> {
    List<Manager> login(String name, String password);
    List<Manager> find(@Param("mno") int mno, @Param("mname") String mname);
}
