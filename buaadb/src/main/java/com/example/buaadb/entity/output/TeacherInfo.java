package com.example.buaadb.entity.output;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class TeacherInfo {
    @TableId
    private String tno;
    private String tname;
    private String tsex;
    private String ttitle;
    private Integer scno;
    private String scname;
}
