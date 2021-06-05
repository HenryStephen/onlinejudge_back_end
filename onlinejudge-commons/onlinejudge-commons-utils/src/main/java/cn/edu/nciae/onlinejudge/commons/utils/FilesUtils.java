package cn.edu.nciae.onlinejudge.commons.utils;


import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

public class FilesUtils {

    /**
     * MultipartFile 转换成 File
     * @param multipartFile
     * @return
     */
    public static File transferToFile(MultipartFile multipartFile) {
//        选择用缓冲区来实现这个转换即使用java 创建的临时文件 使用 MultipartFile.transferto()方法 。
        File file = null;
        try {
            String originalFilename = multipartFile.getOriginalFilename();
            int pointIndex =  originalFilename.lastIndexOf(".");//点号的位置
            String fileSuffix = originalFilename.substring(pointIndex);//截取文件后缀
            String fileNewName = String.valueOf(System.currentTimeMillis()); //新文件名,时间戳形式yyyyMMddHHmmssSSS
            file=File.createTempFile(fileNewName, fileSuffix);
            multipartFile.transferTo(file);
            file.deleteOnExit();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }
}
