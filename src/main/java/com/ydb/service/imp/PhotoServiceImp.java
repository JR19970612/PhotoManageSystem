package com.ydb.service.imp;

import com.ydb.bean.ResultBean;
import com.ydb.dao.IPhotoDao;
import com.ydb.entity.Photo;
import com.ydb.exception.ErrorFileFormatException;
import com.ydb.service.IPhotoService;
import net.coobird.thumbnailator.Thumbnailator;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.util.ThumbnailatorUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

/**
 * @program: com.ydb.dao
 * @description: PhotoServiceImp
 * @author: JR
 * @create: 2018-12-10 10:27
 **/

@Service
public class PhotoServiceImp implements IPhotoService {
    @Autowired
    IPhotoDao photoDao;


    ResultBean<Photo> resultBean;

    @Override
    public ResultBean<Photo> addPhoto(MultipartHttpServletRequest request, Photo photo) throws IOException {
        //判断文件格式
        if (!judgeFormat()) {
            throw new ErrorFileFormatException("上传的文件格式有误");
        }
        //保存图片到本地
        saveOriginalPhoto(request, photo);//原始图片
        saveThumPhoto(request, photo);//缩略图片
        //保存图片信息到数据库
        photoDao.insertPhoto(photo);
        //返回response
        resultBean = new ResultBean<>();
        resultBean.setStatus(ResultBean.SUCCSSED_CODE);
        resultBean.setMsg("添加成功");
        resultBean.setData(Arrays.asList(photo));
        return resultBean;
    }


    @Override
    public ResultBean<Photo> dropPhoto(Integer photoId) {
        photoDao.deletePhoto(photoId);
        resultBean = new ResultBean<>();
        resultBean.setStatus(ResultBean.SUCCSSED_CODE);
        resultBean.setMsg("删除成功");
        resultBean.setData(Arrays.asList(null));
        return resultBean;
    }

    @Override
    public ResultBean<Photo> updatePhoto(Photo photo) {
        photoDao.updatePhoto(photo);
        resultBean = new ResultBean<>();
        resultBean.setStatus(ResultBean.SUCCSSED_CODE);
        resultBean.setMsg("修改成功");
        resultBean.setData(Arrays.asList(photo));
        return resultBean;
    }

    @Override
    public ResultBean<Photo> queryPhoto(Integer photoId) {
        Photo photo = photoDao.selectPhotoById(photoId);
        resultBean = new ResultBean<>();
        resultBean.setStatus(ResultBean.SUCCSSED_CODE);
        resultBean.setMsg("查询成功");
        resultBean.setData(Arrays.asList(photo));
        return resultBean;
    }

    @Override
    public ResultBean<Photo> queryPhoto(String photoName) {
        Photo photo = photoDao.selectPhotoByName(photoName);
        resultBean = new ResultBean<>();
        resultBean.setStatus(ResultBean.SUCCSSED_CODE);
        resultBean.setMsg("查询成功");
        resultBean.setData(Arrays.asList(photo));
        return resultBean;
    }

    private boolean judgeFormat() {
        return false;
    }

    private void saveOriginalPhoto(MultipartHttpServletRequest request, Photo photo) throws IOException {
        MultipartFile multipartFile = request.getFile("photo");
        String contextPath = request.getSession().getServletContext().getRealPath("/") + "/originalphoto";
        File file = new File(contextPath);
        if (!file.exists()) {
            file.mkdir();
        }
        String imageName = photo.getPhotoName() + "-" + System.currentTimeMillis() + "-" + multipartFile.getOriginalFilename();
        File savePath = new File(contextPath, imageName);
        multipartFile.transferTo(savePath);
        photo.setPhotoOriginalUrl("http://localhost:8080/originalphoto/" + imageName);
    }


    private void saveThumPhoto(MultipartHttpServletRequest request, Photo photo) throws IOException {
        MultipartFile multipartFile = request.getFile("photo");
        String contextPath = request.getSession().getServletContext().getRealPath("/") + "/thumphoto";
        File file = new File(contextPath);
        if (!file.exists()) {
            file.mkdir();
        }
        String imageName = photo.getPhotoName() + "-" + System.currentTimeMillis() + "-" + multipartFile.getOriginalFilename();
        File savePath = new File(contextPath, imageName);
        Thumbnails.of(multipartFile.getInputStream())
                .scale(0.25)
                .toFile(savePath);
        photo.setPhotoThumUrl("http://localhost:8080/thumphoto/" + imageName);
    }
}
