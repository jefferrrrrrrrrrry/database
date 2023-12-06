package com.example.buaadb.service;

import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.buaadb.entity.Course;
import com.example.buaadb.entity.School;
import com.example.buaadb.mapper.SchoolMapper;
import org.springframework.stereotype.Service;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

@Service
public class SchoolService extends ServiceImpl<SchoolMapper, School> {
    public void export(HttpServletResponse response, List<School> list) throws IOException {
        ExcelWriter writer = ExcelUtil.getWriter(true);
        writer.write(list);

        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
        String fileName = URLEncoder.encode("学院信息", "UTF-8");
        response.setHeader("Content-Disposition", "attachment;filename=" + fileName + ".xlsx");

        ServletOutputStream out = response.getOutputStream();
        writer.flush(out, true);
        out.close();
        writer.close();
    }
}
