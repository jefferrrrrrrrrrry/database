package com.example.buaadb.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.example.buaadb.entity.output.CourseInfo;
import lombok.Data;

import java.util.List;

@Data
public class Student {
    @TableId
    private String sno;
    private String sname;
    private Integer sage;
    private String ssex;
    private Integer sgrade;
    private Integer scredit;
    private String spassword;
    private Integer clno;

    public static void sortBySno(List<Student> list, int up) {
        list.sort(((o1, o2) -> {
            if (o1.sno.compareTo(o2.sno) > 0) {
                return up;
            } else if (o1.sno.compareTo(o2.sno) == 0) {
                return 0;
            } else {
                return -up;
            }
        }));
    }
}
