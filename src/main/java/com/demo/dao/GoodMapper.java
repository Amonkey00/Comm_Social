package com.demo.dao;


import com.demo.pojo.Good;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface GoodMapper {

    // Get list of goods
    List<Good> getGoodList();

    // Get good by Id
    Good getGoodById(@Param("goodId") int goodId);

    // Add good
    int addGood(Good good);

    // Update good
    int updateGood(Good good);

    // Delete good
    int deleteGood(@Param("goodId") int goodId);


}
