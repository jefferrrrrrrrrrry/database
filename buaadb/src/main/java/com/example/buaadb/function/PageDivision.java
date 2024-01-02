package com.example.buaadb.function;

import com.example.buaadb.common.Result;
import com.example.buaadb.entity.output.CourseInfo;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class PageDivision {
    public static <T> HashMap<String, Object> getPage(List<T> list, Integer pageNum, Integer pageSize) {
        // , @RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "20") Integer pageSize
        if (pageNum == -1) {
            pageSize = 999999999;
        }
        int total = list.size();
        int totalPage = (total / pageSize) + ((total % pageSize > 0) ? 1 : 0);
        List<T> page;
        if (pageNum > totalPage || pageNum <= 0) {
            page = new LinkedList<>();
        } else {
            page = list.subList((pageNum - 1) * pageSize, Math.min(pageNum * pageSize, total));
        }
        HashMap<String, Object> res = new HashMap<>();
        res.put("total", total);
        res.put("totalPage", totalPage);
        res.put("page", page);
        return res;
    }
}
