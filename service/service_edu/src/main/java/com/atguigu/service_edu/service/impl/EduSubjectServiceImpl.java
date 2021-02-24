package com.atguigu.service_edu.service.impl;

import com.alibaba.excel.EasyExcel;
import com.atguigu.service_edu.entity.EduSubject;
import com.atguigu.service_edu.entity.SubjectData;
import com.atguigu.service_edu.listener.SubjectExcelListener;
import com.atguigu.service_edu.mapper.EduSubjectMapper;
import com.atguigu.service_edu.service.EduSubjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2021-02-05
 */
@Service
public class EduSubjectServiceImpl extends ServiceImpl<EduSubjectMapper, EduSubject> implements EduSubjectService {

    //添加课程分类
    @Override
    public void saveSubject(MultipartFile file, EduSubjectService subjectService) {
        try {
            //文件输入流
            InputStream inputStream = file.getInputStream();
            //调用文件进行读取
            EasyExcel.read(inputStream, SubjectData.class,new SubjectExcelListener(subjectService)).sheet();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
