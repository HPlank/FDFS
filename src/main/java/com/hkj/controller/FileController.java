package com.hkj.controller;

import com.hkj.pojo.FileInfo;
import com.hkj.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

//文件控制器，实现上传下载
@Controller
public class FileController {

    @Autowired
    private FileService fileService;

    /**
     * 文件上传
     * @param multipartFile  要上传哪个文件
     * @return
     */
    @RequestMapping("/uploadFile")
    public String uploadFile(MultipartFile uploadFile){

        boolean b = this.fileService.uploadFile(uploadFile);
        System.out.println(b?"上传成功":"上传失败");
//        System.out.println(uploadFile);
////        System.out.println("上传文件原始名称" + uploadFile.getOriginalFilename());
////        System.out.println("上传文件的大小" + uploadFile.getSize());
        return "redirect:/index";
    }

    /**
     * 进入到首页面的方法
     * 在进入首页面前要先查询数据库，查询可以下载文件的相关信息
     * @return
     */
    @RequestMapping(value = {"/","/index"})
    public String toIndex(Model model){
        List<FileInfo> list = this.fileService.getFileInfos();
        model.addAttribute("list",list);
        return "forward:/index.jsp";
    }

    /**
     * 下载文件
     * @param fileInfoId 要下载的文件在数据库中的相关数据主键
     */
    @RequestMapping(value = "/downloadFile/{fileInfoId}")
    public void downloadFile(@PathVariable("fileInfoId") Long fileInfoId, HttpServletResponse response) {
        //获取要下载的数据
        InputStream inputStream = null;
        try {
            inputStream = this.fileService.getFile(fileInfoId);
            FileInfo fileInfo = this.fileService.getFileInfoById(fileInfoId);
            //设置响应头，响应为下载，下载的文件名是什么
            response.setContentType("application/octet-stream");
            response.setHeader("content-disposition", "attachement;filename=" + fileInfo.getFileName());
            OutputStream outputStream = response.getOutputStream();

            byte[] temp = new byte[512];
            int len = 0;
            while ((len = inputStream.read(temp)) != -1) {
                outputStream.write(temp, 0, len);
            }
            //刷新缓存区
            outputStream.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


    }


}
