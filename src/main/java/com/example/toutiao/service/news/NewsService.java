/*
 * Copyright 2001-2017 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.toutiao.service.news;

import com.example.toutiao.domain.news.News;
import com.example.toutiao.mapper.news.NewsMapper;
import com.example.toutiao.service.BaseService;
import com.example.toutiao.utils.image.ImageUtil;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

/**
 * <p> Title: </p>
 *
 * <p> Description: </p>
 *
 * @author: Guo Weifeng
 * @version: 1.0
 * @create: 2019/12/20 13:38
 */
@Service
public class NewsService extends BaseService<NewsMapper, News> {

    /**
     * 将图片保存在服务器中
     * @param file
     * @return
     * @throws IOException
     */
    public String saveImage(MultipartFile file) throws IOException {
        boolean isImage = ImageUtil.isFileAllowed(file);

        if (!isImage) {
            return null;
        }

        String fileName = UUID.randomUUID().toString().replaceAll("-", "") + "." + ImageUtil.getFileExt(file);

        Files.copy(file.getInputStream(), new File(ImageUtil.IMAGE_DIR + fileName).toPath(),
                StandardCopyOption.REPLACE_EXISTING);
        return ImageUtil.WEB_DOMAIN + "image?name=" + fileName;
    }
}