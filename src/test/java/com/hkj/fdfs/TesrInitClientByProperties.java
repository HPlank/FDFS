package com.hkj.fdfs;

import org.csource.common.MyException;
import org.csource.fastdfs.*;

import java.io.IOException;
import java.util.Properties;

public class TesrInitClientByProperties {

    public static void main(String[] args) {
        Properties properties = new Properties();

        try {
            properties.load(TesrInitClientByProperties.class.getClassLoader().getResourceAsStream("test/fdfs.properties"));
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("properties配置文件加载错误！！！");
            return;
        }

        //通过ClientGlobal对象加载配置内容
        try {
            ClientGlobal.initByProperties(properties);

            TrackerClient trackerClient = new TrackerClient();
            TrackerServer trackerServer = trackerClient.getConnection();
            StorageServer storageServer = trackerClient.getStoreStorage(trackerServer);
            StorageClient storageClient = new StorageClient(trackerServer,storageServer);

            System.out.println("trackerClient---->>>" + trackerClient);
            System.out.println("trackerClient---->>>" + trackerClient);
            System.out.println("trackerClient---->>>" + trackerClient);
            System.out.println("trackerClient---->>>" + trackerClient);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (MyException e) {
            e.printStackTrace();
        }

    }


}
