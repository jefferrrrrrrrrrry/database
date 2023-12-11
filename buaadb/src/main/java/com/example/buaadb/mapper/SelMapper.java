package com.example.buaadb.mapper;

import com.example.buaadb.entity.Sel;
import com.example.buaadb.entity.SelExample;
import com.example.buaadb.entity.SelKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SelMapper {
    long countByExample(SelExample example);

    int deleteByExample(SelExample example);

    int deleteByPrimaryKey(SelKey key);

    int insert(Sel record);

    int insertSelective(Sel record);

    List<Sel> selectByExample(SelExample example);

    Sel selectByPrimaryKey(SelKey key);

    int updateByExampleSelective(@Param("record") Sel record, @Param("example") SelExample example);

    int updateByExample(@Param("record") Sel record, @Param("example") SelExample example);

    int updateByPrimaryKeySelective(Sel record);

    int updateByPrimaryKey(Sel record);
}