package com.hkj.mapper;

import com.hkj.pojo.FileInfo;

import java.util.List;

public interface FileInfoMapper {

    /**
     * 新增文件相关数据到数据库
     * @param fileInfo 要新增的数据
     */
    void insertFileInfo(FileInfo fileInfo);
    /**
     * 查询所有的文件相关数据
     * @return
     */
    List<FileInfo> selectFileInfos();
    /**
     * 主键查询详情
     * @param id
     * @return
     */
    FileInfo selectById(Long id);
}
