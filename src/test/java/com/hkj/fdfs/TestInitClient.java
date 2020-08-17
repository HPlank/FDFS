package com.hkj.fdfs;

import org.csource.common.MyException;
import org.csource.fastdfs.*;

import java.io.IOException;

public class TestInitClient {
    public static void main(String[] args) {
        try {
            ClientGlobal.init("src/main/resources/test/fdfs.conf");

            TrackerClient trackerClient = new TrackerClient();
            TrackerServer trackerServer = trackerClient.getConnection();
            StorageServer storageServer = trackerClient.getStoreStorage(trackerServer);
            StorageClient storageClient = new StorageClient(trackerServer, storageServer);

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