package com.ydb.service.imp;

import com.ydb.bean.ResultBean;
import com.ydb.dao.IPhotoDao;
import com.ydb.entity.Photo;
import com.ydb.service.IPhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public ResultBean<Photo> addPhoto(Photo photo) {
        photoDao.insertPhoto(photo);
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
}
