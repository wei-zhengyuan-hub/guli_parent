package com.atguigu.service_edu.service.impl;

import com.alibaba.excel.EasyExcel;
import com.atguigu.service_edu.entity.EduSubject;
import com.atguigu.service_edu.entity.SubjectData;
import com.atguigu.service_edu.entity.subject.OneSubject;
import com.atguigu.service_edu.entity.subject.TwoSubject;
import com.atguigu.service_edu.listener.SubjectExcelListener;
import com.atguigu.service_edu.mapper.EduSubjectMapper;
import com.atguigu.service_edu.service.EduSubjectService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

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

    //课程分类列表（树形）
    @Override
    public List<OneSubject> getAllOneTwoSubject() {

        //查询所有一级分类
        QueryWrapper<EduSubject> wrapperOne = new QueryWrapper<>();
        wrapperOne.eq("parent_id","0");
//        this.list(wrapperOne);
        List<EduSubject> oneSubjectList = baseMapper.selectList(wrapperOne);

        //查询所有二级分类
        QueryWrapper<EduSubject> wrapperTwo = new QueryWrapper<>();
        wrapperTwo.ne("parent_id","0");//parent_id不等于0就是二级分类
        List<EduSubject> twoSubjectList = baseMapper.selectList(wrapperTwo);

        //封装一级分类
        //存储最终封装的数据
        List<OneSubject> listOne = new ArrayList<>();

        for (int i=0; i<oneSubjectList.size(); i++){
            EduSubject eduSubject = oneSubjectList.get(i);

            OneSubject oneSubject = new OneSubject();

            BeanUtils.copyProperties(eduSubject,oneSubject);

            listOne.add(oneSubject);

            List<TwoSubject> listTwo = new ArrayList<>();
            //封装二级分类
            for (int j=0; j<twoSubjectList.size(); j++){
                EduSubject eduSubject1 = twoSubjectList.get(j);
                if (eduSubject1.getParentId().equals(eduSubject.getId())){
                    TwoSubject twoSubject = new TwoSubject();

                    BeanUtils.copyProperties(eduSubject1,twoSubject);

                    listTwo.add(twoSubject);
                }
            }

            oneSubject.setChildren(listTwo);

        }
        return listOne;
    }
}

