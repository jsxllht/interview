package com.csqf.mapper;

import com.csqf.pojo.RocketmqTransactionLog;
import com.csqf.pojo.RocketmqTransactionLogExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RocketmqTransactionLogMapper {
    int countByExample(RocketmqTransactionLogExample example);

    int deleteByExample(RocketmqTransactionLogExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(RocketmqTransactionLog record);

    int insertSelective(RocketmqTransactionLog record);

    List<RocketmqTransactionLog> selectByExample(RocketmqTransactionLogExample example);

    RocketmqTransactionLog selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") RocketmqTransactionLog record, @Param("example") RocketmqTransactionLogExample example);

    int updateByExample(@Param("record") RocketmqTransactionLog record, @Param("example") RocketmqTransactionLogExample example);

    int updateByPrimaryKeySelective(RocketmqTransactionLog record);

    int updateByPrimaryKey(RocketmqTransactionLog record);
}