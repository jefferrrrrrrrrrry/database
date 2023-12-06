package com.example.buaadb.service;

import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.buaadb.controller.logInfo.LogInfo;
import com.example.buaadb.entity.Course;
import com.example.buaadb.entity.Student;
import com.example.buaadb.mapper.StudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

@Service
public class StudentService extends ServiceImpl<StudentMapper, Student> {
    @Autowired
    private StudentMapper mapper;
    public int login(LogInfo logInfo) {
        List<Student> student = mapper.login(logInfo.getUsername(), logInfo.getPassword());
        if (student.isEmpty()) {
            return 1; // no such student
        }
        return 0;
    }

    public void export(HttpServletResponse response, List<Student> list) throws IOException {
        ExcelWriter writer = ExcelUtil.getWriter(true);
        writer.write(list);

        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
        String fileName = URLEncoder.encode("学生信息", "UTF-8");
        response.setHeader("Content-Disposition", "attachment;filename=" + fileName + ".xlsx");

        ServletOutputStream out = response.getOutputStream();
        writer.flush(out, true);
        out.close();
        writer.close();
    }
}
