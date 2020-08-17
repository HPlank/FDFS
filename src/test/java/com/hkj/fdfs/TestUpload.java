package com.hkj.fdfs;

import org.csource.common.MyException;
import org.csource.common.NameValuePair;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.StorageServer;

import java.io.IOException;

public class TestUpload {
    public static void main(String[] args) {

        //获取Storage客户端对象
        StorageClient storageClient = FastDFSClientUtils.getStorageClient();

        //准备文件上传需要的信息

        String local_file = "testFile/f.jpg";
        String file_ext_name = "jpg";
        NameValuePair[] meta_list = new NameValuePair[]{
                new NameValuePair("fileName","f.jpg"),
                new NameValuePair("uploadUser","hkj")
        };
        /**
         * upload_file(String local_filename,String file_ext_name,NameValuePair[] meta_list)
         * 要上传的本地文件的地址 local_filename
         * 要上传的文件的后缀名。FastDFS使用UUID管理要上传的文件命名，需要提供文件的后缀名 file_ext_name
         * 要上传文件的元数据。如：文件的原始名称，文件的大小，文件的创建者等 meta_list
         *
         * 返回值：返回上传后的文件在FastDFS中的卷名和文件名。
         *
         */

        String[] fileIds = null;

        try {
            fileIds = storageClient.upload_file(local_file,file_ext_name,meta_list);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (MyException e) {
            e.printStackTrace();
        }

        //解析上传结果
        System.out.println("返回字符串数组的长度-" + fileIds.length);
        System.out.println("0下标位置-" + fileIds[0]);
        System.out.println("1下标位置-" + fileIds[1]);

    }
}
