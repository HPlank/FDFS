package com.hkj.fdfs;

import org.apache.commons.io.IOUtils;
import org.csource.common.MyException;
import org.csource.common.NameValuePair;
import org.csource.fastdfs.StorageClient;

import java.io.FileOutputStream;
import java.io.IOException;

public class TestDownload {
    public static void main(String[] args) {
        //获取storage客户端对象
        StorageClient storageClient = FastDFSClientUtils.getStorageClient();

        String groupName = "group1";
        String remoteFileName = "M00/00/00/wKgAel85XvuAYVxXAANDtgI5ESU247.jpg";

        byte[] dates = null;
        try {
            dates = storageClient.download_file(groupName,remoteFileName);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (MyException e) {
            e.printStackTrace();
        }

        //查询要下载的文件的元数据，获取文件的原师名称，作为下载文件的文件名
        NameValuePair[] metadata = new NameValuePair[0];
        try {
            metadata = storageClient.get_metadata(groupName,remoteFileName);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (MyException e) {
            e.printStackTrace();
        }

        String fileName = "";
        for(NameValuePair nvp : metadata){
            if(nvp.getName().equals("fileName")){
                fileName = nvp.getValue();
            }
        }

        //处理下载后的数据信息
        try {
            IOUtils.write(dates,new FileOutputStream("downloadFile/"+fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("文件下载成功，下载文件名称是：" + fileName);

    }
}
