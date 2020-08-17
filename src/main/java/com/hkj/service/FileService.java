package com.hkj.service;

import com.hkj.pojo.FileInfo;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.List;

public interface FileService {
    /**
     * 上传文件到FastDFS中
     * @param file
     * @return
     */
    boolean uploadFile(MultipartFile file);

    /**
     * 查询所有可下载文件的数据
     * @return
     */
    List<FileInfo> getFileInfos();

    /**
     * 获取要下载的文件
     * @param fileInfoId 文件在数据库中的主键
     * @return 要下载的文件的输入流
     */
    InputStream getFile(Long fileInfoId);


    FileInfo getFileInfoById(Long fileInfoId);

}
