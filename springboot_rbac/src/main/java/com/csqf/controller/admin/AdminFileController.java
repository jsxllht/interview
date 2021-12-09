package com.csqf.controller.admin;

import com.csqf.common.result.R;
import com.csqf.common.result.ResponseEnum;
import com.csqf.service.FileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

@RestController
@CrossOrigin
@RequestMapping("/admin/file")
@Api(description = "文件接口")
public class AdminFileController {

    @Autowired
    private FileService fileService;

    /**
     * 文件上传
     *
     * @param file
     */
    @ApiOperation("文件上传")
    @PostMapping("/upload")
    //文件上传只能用post
    //springmvc中 上传的文件 用这个类型 MultipartFile
    //module 上传到哪个目录
    public R upload(
            @ApiParam(value = "文件", required = true)
            @RequestParam("file") MultipartFile file,
            @ApiParam(value = "模块", required = true)
            @RequestParam("module") String module) throws IOException {

        // 获得上传文件的 InputStream
        InputStream inputStream = file.getInputStream();
        // 获得上传文件的名字
        String originalFilename = file.getOriginalFilename();
        String uploadUrl = fileService.upload(inputStream, module, originalFilename);

        //返回r对象
        return new R(ResponseEnum.SUCCESS, uploadUrl);
    }

}
