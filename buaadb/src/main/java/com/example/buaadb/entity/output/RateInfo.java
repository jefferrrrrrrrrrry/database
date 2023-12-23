package com.example.buaadb.entity.output;

import lombok.Data;

//SELECT rate, COUNT(rate) AS num FROM (SELECT *, rate(Segrade) AS rate FROM Sel) AS board GROUP BY rate
@Data
public class RateInfo {
    private String rate;
    private Integer num;
}
