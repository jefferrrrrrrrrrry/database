package com.example.buaadb.mapper;

import com.example.buaadb.entity.output.RateInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface EchartsMapper {
    @Select("SELECT rate AS name, COUNT(rate) AS value FROM (SELECT *, rate(Segrade) AS rate FROM Sel WHERE cno = ${cno}) AS board GROUP BY rate")
    List<RateInfo> getRateCno(@Param("cno") String cno);
    @Select("SELECT rate AS name, COUNT(rate) AS value FROM (SELECT *, rate(Segrade) AS rate FROM Sel WHERE sno = ${sno}) AS board GROUP BY rate")
    List<RateInfo> getRateSno(@Param("cno") String sno);

}
