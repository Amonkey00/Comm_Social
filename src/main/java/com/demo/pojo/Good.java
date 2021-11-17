package com.demo.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Good {
    
    private int goodId;
    private int saleId;
    private String goodType;
    private String goodName;
    private String goodDescription;
    private int goodPrice;
    private String state;
    
}
