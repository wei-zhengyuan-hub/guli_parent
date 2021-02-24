package com.atguigu.service_edu.service;

import com.atguigu.service_edu.entity.EduSubject;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 * 课程科目 服务类
 * </p>
 *
 * @author testjava
 * @since 2021-02-05
 */
public interface EduSubjectService extends IService<EduSubject> {
    //excel 文件读取----------添加课程分类
    void saveSubject(MultipartFile file, EduSubjectService subjectService);

}
