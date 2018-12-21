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

    @Autowired
    ApplicationContext applicationContext;

    @Override
    public ResultBean<Photo> addPhoto(MultipartHttpServletRequest request, Photo photo) throws IOException {
        int code = -1;
        ResultBean<Photo> resultBean = new ResultBean();
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
            code = photoDao.insertPhoto(photo);
        } catch (Exception e) {
            photoUtil.dropPhoto(photo);
            throw e;
        }
        initResultBean(code, resultBean);
        resultBean.setData(Arrays.asList(photo));
        return resultBean;
    }


    @Override
    public ResultBean<Photo> dropPhoto(Photo photo) {
        ResultBean<Photo> resultBean = new ResultBean();
        //删除数据库图片信息
        int code = photoDao.deletePhoto(photo);
        //删除本地磁盘图片
        photoUtil.dropPhoto(photo);
        initResultBean(code, resultBean);
        resultBean.setData(Arrays.asList());
        return resultBean;
    }

    @Override
    public ResultBean<Photo> updatePhoto(Photo photo) {
        ResultBean<Photo> resultBean = new ResultBean();
        int code = photoDao.updatePhoto(photo);
        initResultBean(code, resultBean);
        resultBean.setData(Arrays.asList(photo));
        return resultBean;
    }

    @Override
    public ResultBean<Photo> queryPhoto(Integer photoId) {
        ResultBean<Photo> resultBean = new ResultBean();
        List<Photo> photos = photoDao.selectPhotoById(photoId);
        resultBean = new ResultBean<>();
        resultBean.setStatus(ResultBean.SUCCSSED_CODE);
        resultBean.setMsg("查询成功");
        resultBean.setData(photos);
        return resultBean;
    }

    @Override
    public ResultBean<Photo> queryPhoto(String photoName) {
        ResultBean<Photo> resultBean = new ResultBean();
        List<Photo> photos = photoDao.selectPhotoByName(photoName);
        resultBean = new ResultBean<>();
        resultBean.setStatus(ResultBean.SUCCSSED_CODE);
        resultBean.setMsg("查询成功");
        resultBean.setData(photos);
        return resultBean;
    }

    @Override
    public ResultBean<Photo> queryPhoto(Integer pageSize, Integer pageNum) {
        ResultBean<Photo> resultBean = new ResultBean();
        PageHelper.startPage(pageNum, pageSize);
        Page<Photo> photos = photoDao.selectAllPhoto();
        resultBean = new ResultBean<>();
        resultBean.setStatus(ResultBean.SUCCSSED_CODE);
        resultBean.setMsg("查询成功");
        resultBean.setData(photos.getResult());
        return resultBean;
    }

    private void initResultBean(int code, ResultBean resultBean) {
        if (code > 0) {
            //插入数据成功
            resultBean.setStatus(ResultBean.SUCCSSED_CODE);
            resultBean.setMsg("操作成功");
        } else {
            resultBean.setStatus(ResultBean.FAILURE_CODE);
            resultBean.setMsg("操作失败");
        }
    }
}
