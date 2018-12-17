package com.ydb.service.imp;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.ydb.bean.ResultBean;
import com.ydb.dao.IPhotoDao;
import com.ydb.entity.Photo;
import com.ydb.exception.FomatTypeException;
import com.ydb.service.IPhotoService;
import com.ydb.utils.PhotoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

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

    @Autowired
    PhotoUtil photoUtil;

    ResultBean resultBean;

    @Autowired
    ApplicationContext applicationContext;

    @Override
    public ResultBean<Photo> addPhoto(MultipartHttpServletRequest request, Photo photo) throws IOException {

        MultipartFile multipartFile = request.getFile("photo");
        //判断文件格式
        if (!photoUtil.judgeFormat(multipartFile)) {
            throw new FomatTypeException("上传的文件格式有误");
        }
        //保存图片到本地
        photoUtil.saveImage(multipartFile, photo);
        //保存图片信息到数据库
        photo.setPhotoCreateTime(new Date());
        //出现异常删除本地图片
        try {
            photoDao.insertPhoto(photo);
        } catch (Exception e) {
            photoUtil.dropPhoto(photo);
            throw e;
        }
        //返回response
        resultBean = new ResultBean<>();
        resultBean.setStatus(ResultBean.SUCCSSED_CODE);
        resultBean.setMsg("添加成功");
        resultBean.setData(Arrays.asList(photo));
        return resultBean;
    }


    @Override
    public ResultBean<Photo> dropPhoto(Photo photo) {
        //删除数据库图片信息
        photoDao.deletePhoto(photo.getPhotoId());
        //删除本地磁盘图片
        photoUtil.dropPhoto(photo);
        //返回reponse数据
        resultBean = new ResultBean<>();
        resultBean.setStatus(ResultBean.SUCCSSED_CODE);
        resultBean.setMsg("删除成功");
        resultBean.setData(Arrays.asList());
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
        List<Photo> photos = photoDao.selectPhotoById(photoId);
        resultBean = new ResultBean<>();
        resultBean.setStatus(ResultBean.SUCCSSED_CODE);
        resultBean.setMsg("查询成功");
        resultBean.setData(photos);
        return resultBean;
    }

    @Override
    public ResultBean<Photo> queryPhoto(String photoName) {
        List<Photo> photos = photoDao.selectPhotoByName(photoName);
        resultBean = new ResultBean<>();
        resultBean.setStatus(ResultBean.SUCCSSED_CODE);
        resultBean.setMsg("查询成功");
        resultBean.setData(photos);
        return resultBean;
    }

    @Override
    public ResultBean<List<Photo>> queryPhoto(Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum,pageSize);
        Page<Photo> photos = photoDao.selectAllPhoto();
        resultBean = new ResultBean<>();
        resultBean.setStatus(ResultBean.SUCCSSED_CODE);
        resultBean.setMsg("查询成功");
        resultBean.setData(photos.getResult());
        return resultBean;
    }

}
