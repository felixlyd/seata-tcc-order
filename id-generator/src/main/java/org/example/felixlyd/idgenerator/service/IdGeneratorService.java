package org.example.felixlyd.idgenerator.service;

import java.util.List;

/**
 * class classname
 *
 * @author : liuyaodong
 * @date 2022/10/18
 */
public interface IdGeneratorService {

    /**
     * 取号器-单个
     * @param bizType 指定生成区间
     * @return id号
     */
    Long nextId(String bizType);

    /**
     * 取号器-多个
     * @param bizType 指定生成区间
     * @param batchSize 号码个数
     * @return id号列表
     */
    List<Long > nextId(String bizType, int batchSize);
}
