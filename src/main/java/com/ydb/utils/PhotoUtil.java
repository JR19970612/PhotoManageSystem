package com.ydb.utils;

import com.ydb.entity.Photo;
import net.coobird.thumbnailator.Thumbnails;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.io.File;
import java.io.IOException;
import java.util.regex.Pattern;

/**
 * @program: com.ydb.dao
 * @description: 图片操作工具类
 * @author: JR
 * @create: 2018-12-10 10:27
 **/

@Component
public class PhotoUtil implements ServletContextListener {

    private String realPath;
    private String contextPath;

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        ServletContext servletContext = servletContextEvent.getServletContext();
//        realPath = servletContext.getRealPath("/");
        realPath = "D:\\";
        contextPath = "http://localhost:8080" + servletContext.getContextPath();
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }

    //判断文件格式
    public boolean judgeFormat(MultipartFile multipartFile) {
        String fileName= multipartFile.getOriginalFilename();
        if (fileName == null && !Pattern.matches("(.JPEG|.jpeg|.JPG|.jpg|.png|.PNG|.bmp|.BMP)$",fileName)) {
            return false;
        }
        return true;
    }

    public void saveImage(MultipartFile multipartFile, Photo photo) throws IOException {
        saveOriginalPhoto(multipartFile, photo);
        saveThumPhoto(multipartFile, photo);
    }

    //保存原图
    private void saveOriginalPhoto(MultipartFile multipartFile, Photo photo) throws IOException {
        File file = new File(realPath + "/originalphoto");
        if (!file.exists()) {
            file.mkdir();
        }
        String imageName = photo.getPhotoName() + "-" + System.currentTimeMillis() + "-" + multipartFile.getOriginalFilename();
        File savePath = new File(file, imageName);
        multipartFile.transferTo(savePath);
        photo.setPhotoOriginalUrl(contextPath + "/originalphoto/" + imageName);
    }


    //保存缩略图
    private void saveThumPhoto(MultipartFile multipartFile, Photo photo) throws IOException {
        File file = new File(realPath + "/thumphoto");
        if (!file.exists()) {
            file.mkdir();
        }
        String imageName = photo.getPhotoName() + "-" + System.currentTimeMillis() + "-" + multipartFile.getOriginalFilename();
        File savePath = new File(file, imageName);
        String[] originalUrl = photo.getPhotoOriginalUrl().split("/");
        String originImagePath = realPath + "/originalphoto/" + originalUrl[originalUrl.length - 1];
        Thumbnails.of(originImagePath)
                .width(400)
                .toFile(savePath);
        photo.setPhotoThumUrl(contextPath + "/thumphoto/" + imageName);
    }


    //删除图片
    public void dropPhoto(Photo photo) {
        String[] originalUrl = photo.getPhotoOriginalUrl().split("/");
        String[] thumUrl = photo.getPhotoThumUrl().split("/");
        File thumPhoto = new File(realPath + "/thumphoto/" + thumUrl[thumUrl.length - 1]);
        File originalPhoto = new File(realPath + "/originalphoto" + originalUrl[originalUrl.length - 1]);
        if (thumPhoto.exists()) {
            thumPhoto.delete();
        }
        if (originalPhoto.exists()) {
            originalPhoto.delete();
        }
    }


}
