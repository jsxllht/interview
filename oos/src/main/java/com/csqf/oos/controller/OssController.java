//package com.csqf.oos.controller;
//
//import com.csqf.common.result.R;
//import com.csqf.common.result.ResponseEnum;
//import com.csqf.oos.service.OssService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//
//@Controller
//public class OssController {
//
//    @Autowired
//    private OssService ossService;
//
//    /**
//     *  根据路径删除文件
//     */
//    @DeleteMapping("/del")
//    public R delFile(@RequestBody String url){
//        ossService.delFile(url);
//        return new R(ResponseEnum.SUCCESS,null);
//    }
//
//}
