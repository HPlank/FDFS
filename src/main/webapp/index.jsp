<%--
  Created by IntelliJ IDEA.
  User: 78749
  Date: 2020/8/16
  Time: 18:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>FastDFS </title>
</head>
<body>

    <center>
        <form method="post" enctype="multipart/form-data" action="/uploadFile">
            选择要上传的文件：<input type="file" name="uploadFile">
            &nbsp;&nbsp;&nbsp;
            <input type="submit" value="上传">
        </form>
        <table border="1" style="width: 800px">
            <thead>
            <tr>
                <th>文件原始名称</th>
                <th>文件卷标名称</th>
                <th>文件远程名称</th>
                <th>操作</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${list}" var="fileInfo">
            <tr>
                <th>${fileInfo.fileName}</th>
                <th>${fileInfo.groupName}</th>
                <th>${fileInfo.remoteFileName}</th>
                <th><a href="#">下载</a>&nbsp;&nbsp;&nbsp;
                    <a href="http://192.168.0.122:8888/${fileInfo.filePath}" target="_blank">预览</a> </th>
            </tr>
            </c:forEach>
            </tbody>
        </table>
    </center>

</body>
</html>
