package com.example.buaadb.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.buaadb.entity.Sel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface SelMapper extends BaseMapper<Sel> {
    void insert(@Param("cno") String cno, @Param("sno") String sno);
    void setscore(@Param("cno") String cno, @Param("sno") String sno, @Param("segrade") Integer segrade);
    void del(@Param("cno") String cno, @Param("sno") String sno);
}
