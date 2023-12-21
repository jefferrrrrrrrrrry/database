package com.example.buaadb.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.buaadb.entity.Sel;
import com.example.buaadb.entity.output.CourseInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface SelMapper extends BaseMapper<Sel> {
    void setscore(@Param("cno") String cno, @Param("sno") String sno, @Param("segrade") Integer segrade);
    void del(@Param("cno") String cno, @Param("sno") String sno);
    List<CourseInfo> find(@Param("cno") String cno);
}
