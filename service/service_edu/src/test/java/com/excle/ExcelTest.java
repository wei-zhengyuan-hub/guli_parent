package com.excle;

import com.alibaba.excel.EasyExcel;

import java.util.ArrayList;
import java.util.List;

public class ExcelTest {

    public static void main(String[] args) {

        String fileName = "F:\\1.xlsx";
        //向excle中写内容
        EasyExcel.write(fileName,DemoData.class).sheet("学生信息").doWrite(data());
        //读取excle中的内容
        EasyExcel.read(fileName,DemoData.class,new ExcelListener()).sheet().doRead();

    }

    public static List<DemoData> data(){
        List<DemoData> list = new ArrayList<>();
        for (int i=0; i<10 ; i++){
            DemoData demoData = new DemoData();
            demoData.setSno(i);
            demoData.setSname("学生"+i);
            list.add(demoData);
        }
        return list;
    }

}
