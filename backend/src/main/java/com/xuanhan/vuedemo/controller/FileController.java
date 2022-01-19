package com.xuanhan.vuedemo.controller;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpResponse;
import com.xuanhan.vuedemo.common.Result;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;

@RestController
@RequestMapping(value = "/files")
public class FileController {

//    private String port = "9090";
    private String port = "9090";

    private String ip = "45.76.231.189";
//    private static String ip = "http://localhost";

    @PostMapping(value = "/upload")
    public Result<?> upload(MultipartFile file) throws IOException {
        String originalFilename = file.getOriginalFilename();
        String uid = IdUtil.fastSimpleUUID();
//        String rootFilePath = System.getProperty("user.dir") + "/backend/src/main/resources/files/" + uid + "_" + originalFilename;
        String rootFilePath = "/home/user/server/dist/files/" + uid + "_" + originalFilename;
        FileUtil.writeBytes(file.getBytes(), rootFilePath);
        String data = "http://" + ip + ":" + port + "/files/" + uid;
        System.out.println(data + "!!");
        Result<String> result = new Result();
        result.setMessage(data);
        result.setData(data);
        return result;
    }

    @GetMapping(value = "/{fileName}")
    public void download(HttpServletResponse response, @PathVariable String fileName) throws IOException {
        OutputStream os;
//        String basePath = System.getProperty("user.dir") + "/backend/src/main/resources/files/";
        String basePath = "/home/user/server/dist/files/";
        List<String> fileNames = FileUtil.listFileNames(basePath);
        String target = fileNames.stream().filter(name -> name.contains(fileName)).findAny().orElse("");
        try {
            if (StrUtil.isNotBlank(target)) {
                response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(target, "UTF-8"));
                response.setContentType("application/octet-stream");
                byte[] bytes = FileUtil.readBytes(basePath + target);
                os = response.getOutputStream();
                os.write(bytes);
                os.flush();
                os.close();
            }
        } catch (Exception e) {
            System.out.println("Fails");
        }
    }
}
