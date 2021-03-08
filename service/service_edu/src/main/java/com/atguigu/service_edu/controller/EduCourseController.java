package com.atguigu.service_edu.controller;


import com.atguigu.service_edu.service.EduCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2021-03-08
 */
@RestController
@RequestMapping("/eduservice/course")
@CrossOrigin//跨域
public class EduCourseController {

    @Autowired
    private EduCourseService eduCourseService;



}

