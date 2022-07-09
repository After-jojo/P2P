package com.wangc.p2p.oss.controller;

import com.wangc.p2p.common.exception.BusinessException;
import com.wangc.p2p.common.result.R;
import com.wangc.p2p.common.result.ResponseEnum;
import com.wangc.p2p.oss.service.FileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author After拂晓
 * @date 2022年05月20日 21:31
 */
@Api(tags = "文件管理")
//@CrossOrigin
@RestController
@RequestMapping("/api/oss/file")
public class FileController {

    @Resource
    private FileService fileService;

    @ApiOperation("文件上传")
    @PostMapping("/upload")

    public R upload(@ApiParam(value = "文件", required = true)
                        @RequestParam("file") MultipartFile multipartFile,
                    @ApiParam(value = "模块", required = true)
                     @RequestParam("module") String module){
        InputStream inputStream = null;
        try {
            inputStream = multipartFile.getInputStream();
            String originalFilename = multipartFile.getOriginalFilename();
            String url = fileService.upload(inputStream, module, originalFilename);
            return R.ok().message("文件上传成功").data("url", url);
        } catch (IOException e) {
            throw new BusinessException(ResponseEnum.UPLOAD_ERROR);
        }
    }
    @ApiOperation("删除文件")
    @DeleteMapping("/remove")
    @ApiParam(value = "要删除的文件", required = true)
    public R remove(@RequestParam("url") String url){
        fileService.removeFile(url);
        return R.ok().message("删除成功");
    }
}
