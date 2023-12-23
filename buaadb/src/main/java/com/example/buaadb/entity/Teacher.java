package com.example.buaadb.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.List;

@Data
public class Teacher {
    @TableId
    private String tno;
    private String tname;
    private String tsex;
    private String ttitle;
    private Integer scno;
    private String tpassword;

    public static void sortByTno(List<Teacher> list, int up) {
        list.sort(((o1, o2) -> {
            if (o1.tno.compareTo(o2.tno) > 0) {
                return up;
            } else if (o1.tno.compareTo(o2.tno) == 0) {
                return 0;
            } else {
                return -up;
            }
        }));
    }
}
