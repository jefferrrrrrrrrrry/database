package com.example.buaadb.entity.output;

import lombok.Data;

//SELECT rate AS name, COUNT(rate) AS value FROM (SELECT *, rate(Segrade) AS rate FROM Sel) AS board GROUP BY rate
@Data
public class RateInfo {
    private String name;
    private Integer value;
}
