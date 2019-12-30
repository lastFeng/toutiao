package com.example.toutiao.controller;

import com.example.toutiao.service.news.NewsService;
import com.example.toutiao.utils.image.ImageUtil;
import com.example.toutiao.utils.json.JsonMapper;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * @author guowf
 * @mail: guowf_buaa@163.com
 * @date created in 2019/12/30 22:00
 * @description:
 */
@Controller
public class NewsController {

    private static final Logger logger = LoggerFactory.getLogger(NewsController.class);

    private JsonMapper jsonMapper = JsonMapper.INSTANCE;

    @Autowired
    private NewsService newsService;

    /**
     * 图片上传
     * @param file
     * @return
     */
    @RequestMapping(value = {"/uploadImage/"}, method = RequestMethod.POST)
    @ResponseBody
    public String uploadImage(@RequestParam("file") MultipartFile file) {

        try {
            String fileUrl = newsService.saveImage(file);

            if (StringUtils.isBlank(fileUrl)) {
                return jsonMapper.getJsonString(1, "上传失败");
            }
            return jsonMapper.getJsonString(0, fileUrl);
        } catch (Exception e) {
            logger.error("上传失败", e.getMessage());
            return jsonMapper.getJsonString(1, "上传失败。");
        }
    }

    /**
     * 将图片展示在页面中
     * @param name
     * @param response
     */
    @RequestMapping(value = "/image", method = RequestMethod.GET)
    @ResponseBody
    public void getImage(@RequestParam("name") String name, HttpServletResponse response) {
        response.setContentType("image/jpg");
        try {
            StreamUtils.copy(new FileInputStream(new File(ImageUtil.IMAGE_DIR + name)),
                    response.getOutputStream());
        } catch (FileNotFoundException e) {
            logger.error("读取文件错误", e.getMessage());
        } catch (IOException e) {
            logger.error("写入文件错误", e.getMessage());
        }
    }
}
