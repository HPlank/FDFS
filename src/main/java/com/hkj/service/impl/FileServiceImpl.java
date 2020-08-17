package com.hkj.service.impl;

import com.hkj.fdfs.FastDFSCommonsUtils;
import com.hkj.mapper.FileInfoMapper;
import com.hkj.pojo.FileInfo;
import com.hkj.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

@Service
public class FileServiceImpl implements FileService {

    @Autowired
    private FileInfoMapper fileInfoMapper;

    public boolean uploadFile(MultipartFile file) {
        try {
            String[] result = FastDFSCommonsUtils.upload(file.getInputStream(), file.getName());
            if(result == null){
                return false;
            }
            System.out.println(Arrays.toString(result));
            FileInfo info = new FileInfo();
            info.setFileName(file.getOriginalFilename());
            info.setGroupName(result[0]);
            info.setRemoteFileName(result[1]);
            info.setFilePath(result[0] + "/" + result[1]);
            this.fileInfoMapper.insertFileInfo(info);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<FileInfo> getFileInfos() {
        return this.fileInfoMapper.selectFileInfos();
    }

    public InputStream getFile(Long fileInfoId) {
        //访问数据库，获取要下载的文件的详细信息
        FileInfo fileInfo = this.fileInfoMapper.selectById(fileInfoId);

        //访问FastDFS，获取要下载文件的具体内容
        InputStream inputStream = FastDFSCommonsUtils.download(fileInfo.getGroupName(),fileInfo.getRemoteFileName());

        return inputStream;
    }

    public FileInfo getFileInfoById(Long fileInfoId) {
        return this.fileInfoMapper.selectById(fileInfoId);
    }

}
