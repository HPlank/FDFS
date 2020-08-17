package com.hkj.fdfs;

import org.csource.fastdfs.*;

public class FastDFSClientUtils {

    //配置文件所在位置
    private static final String configFile = "src/main/resources/test/fdfs.conf";

    private static StorageClient storageClient;
    private static TrackerServer trackerServer;
    private static TrackerClient trackerClient;
    private static StorageServer storageServer;

    static {
        try {
            //使用conf配置文件初始化环境。就是加载连接超时，网络超时，tracker服务器列表等
            ClientGlobal.init(configFile);
            //this.initByProperties();
            trackerClient = new TrackerClient();
            //创建TrackerServer的连接对象
            trackerServer = trackerClient.getConnection();
            //c创建storage服务器的连接对象
            storageServer = trackerClient.getStoreStorage(trackerServer);
            //创建storage服务器的客户端操作对象，可以实现文件的读写操作
            storageClient = new StorageClient(trackerServer,storageServer);
        }catch (Exception e){
            e.printStackTrace();
            throw new ExceptionInInitializerError(e);
        }
    }

    public static StorageClient getStorageClient(){
        return storageClient;
    }

    public static void main(String[] args) {
        System.out.println(getStorageClient());
    }

}
