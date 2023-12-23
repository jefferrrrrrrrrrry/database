package com.example.buaadb.entity.output;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.List;

@Data
public class CourseInfo {
    @TableId
    private String cno;
    private String cname;
    private String cpos;
    private Integer tno;
    private String tname;
    private String ctype;
    private Integer ccredit;
    private Integer ccapacity;
    private Integer cremain;
    private Integer score;

    public static void sortByCno(List<CourseInfo> list, int up) {
        list.sort(((o1, o2) -> {
            if (o1.cno.compareTo(o2.cno) > 0) {
                return up;
            } else if (o1.cno.compareTo(o2.cno) == 0) {
                return 0;
            } else {
                return -up;
            }
        }));
    }

    public static void sortByScore(List<CourseInfo> list, int up) {
        list.sort(((o1, o2) -> {
            if (o1.score > o2.score) {
                return up;
            } else if (o1.score.equals(o2.score)) {
                return 0;
            } else {
                return -up;
            }
        }));
    }
}
