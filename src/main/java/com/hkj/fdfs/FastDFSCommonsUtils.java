package com.hkj.fdfs;

import org.csource.common.MyException;
import org.csource.common.NameValuePair;
import org.csource.fastdfs.*;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Fast'DFS工具类
 */
public class FastDFSCommonsUtils {

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

    /**
     * 上传文件方法
     * @param inputStream 要上传的文件的输入流，用于读取文件内容
     * @param fileName 要上传的文件的原始文件名
     * @return
     */
    public static String[] upload(InputStream inputStream,String fileName){
        try {
        NameValuePair[] metaList = new NameValuePair[]{
                new NameValuePair("fileName",fileName)
        };


            String substring = fileName.substring(fileName.lastIndexOf(".") + 1);
            byte[] dates = new byte[inputStream.available()];
            inputStream.read(dates);
            String[] result = storageClient.upload_file(dates, substring, metaList);
            return result;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } catch (MyException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static InputStream download(String groupName, String remoteFileName){
        try{
            byte[] datas = storageClient.download_file(groupName, remoteFileName);
            return new ByteArrayInputStream(datas);
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

}
