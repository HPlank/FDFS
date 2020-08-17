package com.hkj.fdfs;

import org.csource.common.MyException;
import org.csource.fastdfs.StorageClient;

import java.io.IOException;

public class TestDelete {

    public static void main(String[] args) {

        StorageClient storageClient = FastDFSClientUtils.getStorageClient();

        String groupName = "group1";
        String remoteFileName = "M00/00/00/wKgAel85bFWADmL2ABsOKWXMFMM757.jpg";
        int i = 6;
        try {
            i = storageClient.delete_file(groupName, remoteFileName);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (MyException e) {
            e.printStackTrace();
        }

        System.out.println(i);


    }

}
