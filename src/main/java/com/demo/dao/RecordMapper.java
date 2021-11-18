package com.demo.dao;

import com.demo.pojo.Record;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface RecordMapper {

    List<Record> getRecordByUserId(@Param("userId") int userId);

    int addRecord(Record record);

    int countRecord();
}
