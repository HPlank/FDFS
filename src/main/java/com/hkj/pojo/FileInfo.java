package com.hkj.pojo;

import java.io.Serializable;
import java.util.Objects;

public class FileInfo implements Serializable {

    private Long id;
    private String fileName;
    private String groupName;
    private String remoteFileName;
    private String filePath;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FileInfo fileInfo = (FileInfo) o;
        return Objects.equals(id, fileInfo.id) &&
                Objects.equals(fileName, fileInfo.fileName) &&
                Objects.equals(groupName, fileInfo.groupName) &&
                Objects.equals(remoteFileName, fileInfo.remoteFileName) &&
                Objects.equals(filePath, fileInfo.filePath);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fileName, groupName, remoteFileName, filePath);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getRemoteFileName() {
        return remoteFileName;
    }

    public void setRemoteFileName(String remoteFileName) {
        this.remoteFileName = remoteFileName;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public String toString() {
        return "FileInfo{" +
                "id=" + id +
                ", fileName='" + fileName + '\'' +
                ", groupName='" + groupName + '\'' +
                ", remoteFileName='" + remoteFileName + '\'' +
                ", filePath='" + filePath + '\'' +
                '}';
    }
}
