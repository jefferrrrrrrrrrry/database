package com.example.buaadb.function;

import com.example.buaadb.common.Result;
import com.example.buaadb.entity.output.CourseInfo;

import java.util.HashMap;
import java.util.List;

public class PageDivision {
    public static <T> HashMap<String, Object> getPage(List<T> list, Integer pageNum, Integer pageSize) {
        int total = list.size();
        int totalPage = (total / pageSize) + ((total % pageSize > 0) ? 1 : 0);
        if (pageNum > totalPage || pageNum <= 0) {
            return null;
        }
        List<T> page = list.subList((pageNum - 1) * pageSize, Math.min(pageNum * pageSize, total));
        HashMap<String, Object> res = new HashMap<>();
        res.put("total", total);
        res.put("totalPage", totalPage);
        res.put("page", page);
        return res;
    }
}
